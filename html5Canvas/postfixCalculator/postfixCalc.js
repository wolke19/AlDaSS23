let c = document.getElementById("c"),
    w = c.width = window.innerWidth,
    h = c.height = window.innerHeight,
    ctx = c.getContext( '2d' ),

    next = {
        cx: 150,
        cy: 150,
        w: 200,
        h: 50,
        rightBoundary: 0,
        leftBoundary: 0,
        upperBoundary: 0,
        lowerBoundary: 0
    }
    // background = new Image(w, h),
    next.rightBoundary = next.cx + next.w/2;
    next.leftBoundary = next.cx - next.w/2;
    next.upperBoundary = next.cy - next.h/2;
    next.lowerBoundary = next.cy + next.h/2;


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
    if (event.x > next.leftBoundary &&
        event.x < next.rightBoundary &&
        event.y > next.upperBoundary &&
        event.y < next.lowerBoundary) {
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
    ctx.fillText("NEXT", next.cx, next.cy, 200 );

    handleInputBar();
    handleStack();
    requestAnimationFrame( animate );
}

animate();

