const buttons = document.getElementsByClassName("read_more_button");
const undercolor = document.getElementsByClassName("read_more_undercolor");
const cards = document.getElementsByClassName("cards");
const labels = document.getElementsByClassName("read_more_label");

for(let i = 0; i < cards.length; i++){
	cards[i].addEventListener("mouseenter", () => {
		undercolor[i].style.animationName = "scaleButton";
	})
	
	cards[i].addEventListener("mouseleave", () => {
		undercolor[i].style.animationName = "none";		
	})
}

for(let i = 0; i < buttons.length; i++){
	buttons[i].addEventListener("mouseenter", () => {
		labels[i].style.transform = 'translate(-20px, 50%) scaleX(1.2)';
	})
	
	buttons[i].addEventListener("mouseleave", () => {
		labels[i].style.transform = 'translate(-20px, 50%) scaleX(0)';
	})
}