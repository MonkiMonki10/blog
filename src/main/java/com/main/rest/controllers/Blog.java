package com.main.rest.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.rest.models.Post;
import com.main.rest.repo.PostRepository;

@Controller
public class Blog
{
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/blog")
	public String Main(Model model)
	{
		Iterable<Post> posts = postRepository.findAll();
		
		
		for(Post p: posts)
		{
			String title = p.getTitle();
			int postTitleLength = title.length();
			
			String preview= p.getPreview();
			int postPreviewLength = preview.length();
			
			if(postTitleLength > 20)
				title = title.substring(0, 20) + "...";
			
			if(postPreviewLength > 30)
				preview = preview.substring(0, 30) + "...";
			
			p.setTitle(title);
			p.setPreview(preview);
		}
		
		model.addAttribute("posts", posts);
		return "blog";
	}
	
	@GetMapping("/blog/create") public String GetCreate(Model model){return "create";}
	
	@PostMapping("/blog/create")
	public String PostCreate(@RequestParam String title, @RequestParam String preview, @RequestParam String content, Model model)
	{
		Post post = new Post();
		post.setTitle(title);
		post.setPreview(preview);
		post.setText(content);
		postRepository.save(post);
		return "redirect:/blog";
	}
	
	@GetMapping("/blog/{id}")
	public String BlogDetails(@PathVariable(value = "id") long id, Model model)
	{
		Post post = postRepository.findById(id).orElseThrow();
		post.setViews(post.getViews() + 1);
		postRepository.save(post);
		model.addAttribute("posts", post);
		return "details";
	}
	
	@GetMapping("/blog/{id}/edit")
	public String GetEdit(@PathVariable(value = "id") long id, Model model)
	{
		if(!postRepository.existsById(id))return "redirect:/blog";
		
		Optional<Post> post = postRepository.findById(id);
		ArrayList<Post> res = new ArrayList<>();
		post.ifPresent(res::add);
		model.addAttribute("posts", res);
		return "edit";
	}

	@PostMapping("/blog/{id}/edit")
	public String PostEdit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String preview, @RequestParam String content, Model model)
	{
		Post post = postRepository.findById(id).orElseThrow();
		post.setTitle(title);
		post.setPreview(preview);
		post.setText(content);
		postRepository.save(post);
		return "redirect:/blog";
	}
	
	@PostMapping("/blog/{id}/remove")
	public String PostRemove(@PathVariable(value = "id") long id, Model model)
	{
		Post post = postRepository.findById(id).orElseThrow();
		postRepository.delete(post);
		return "redirect:/blog";
	}
}
