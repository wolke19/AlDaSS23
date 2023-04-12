let c = document.getElementById("c"),
    w = c.width = window.innerWidth,
    h = c.height = window.innerHeight,
    ctx = c.getContext( '2d' ),

    // background = new Image(w, h),
    tick = 0,
    calcStack = [],
    histStack = [],
    checkInput = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/"];

ctx.fillStyle = "black";
// background.src = "Aufgabe02/andrew-c-31-00000.jpg";




// EVENTS   --------------------------------------------------------------------------------------------------
addEventListener("keypress", function (event){
    if (checkInput.includes(event.key)){
        histStack.push(event.key);

        switch (event.key){
            case "+": calcStack.push(calcStack.pop() + calcStack.pop());
                break;
            case "-": calcStack.push(- calcStack.pop() + calcStack.pop());
                break;
            case "*": calcStack.push(calcStack.pop() * calcStack.pop());
                break;
            case "/": calcStack.push(Math.pow(calcStack.pop() / calcStack.pop(), -1));
                break;
            default: calcStack.push(parseFloat(event.key));
        }
    }
})

addEventListener("click", function (event){
    if (event.x > c.width * 0.15 - 100 &&
        event.x < c.width * 0.15 + 100 &&
        event.y > c.height - 150 &&
        event.y < c.height - 100) {
        window.location = "https://wolke19.github.io/AlDaSS23";
    }
})


// FUNCTIONS --------------------------------------------------------------------------------------------------
function handleInputBar(){
    ctx.fillStyle = "white";
    ctx.fillRect(300, h - 200, w - 600, 100);
    ctx.fillStyle = "black";
    ctx.font = "30px Arial";
    ctx.textAlign = "center";
    ctx.fillText(histStack.join("  "), w/2, h - 135, w - 600);
}
function handleStack(){
    ctx.font = "25px Arial";
    ctx.textAlign = "center";
    for (let i = 0; i < calcStack.length; i++) {
        ctx.fillStyle = "grey";
        ctx.fillRect(w/2 - 100, h - 250  - 30*i, 200, 25);
        ctx.fillStyle = "white";
        ctx.fillText(calcStack[i], w/2, h - 250  - 30*i + 23, 200);
    }
}

function handleEffects(){

}


function animate() {
    ctx.clearRect(0,0,c.width, c.height); // ctx.drawImage(background, 0, 0, );
    tick++;
    ctx.fillStyle = "grey";
    ctx.textAlign = "center";
    ctx.font = "30px Arial";
    ctx.fillText("Aufgabe 2 - Postfix Calculator", w/2, 100, w);
    ctx.fillText("NEXT", c.width * 0.15, c.height -100 , 200 );

    handleInputBar();
    handleStack();
    requestAnimationFrame( animate );
}

animate();

