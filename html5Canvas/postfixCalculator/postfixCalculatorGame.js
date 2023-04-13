let c = document.getElementById("c"),
    w = c.width = window.innerWidth,
    h = c.height = window.innerHeight,
    ctx = c.getContext( '2d' ),

    goal = 10,
    score = 0,
    remainingChars = 20,
    calcStack = [],
    histStack = [],
    checkInput = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/"],

    next = {
        cx: 150,
        cy: 150,
        w: 200,
        h: 50,
        rightBoundary: 0,
        leftBoundary: 0,
        upperBoundary: 0,
        lowerBoundary: 0
    };

next.rightBoundary = next.cx + next.w/2;
next.leftBoundary = next.cx - next.w/2;
next.upperBoundary = next.cy - next.h/2;
next.lowerBoundary = next.cy + next.h/2;

// let background = new Image(w, h);
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
            case "/": calcStack.push((Math.pow(calcStack.pop() / calcStack.pop(), -1)).toFixed(2));
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
    ctx.fillStyle = "grey";
    ctx.font = "12px Arial";
    ctx.textAlign = "left";
    ctx.fillText("warte auf Tastatureingabe ...", 310, h-180, 200);
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
//     TODO
}

function handleText(){
    ctx.fillStyle = "grey";
    ctx.textAlign = "center";
    ctx.font = "30px Arial";
    ctx.fillText("Aufgabe 2 - Postfix Calculator", w/2, 100, w);
    ctx.fillText("NEXT", next.cx, next.cy, 200 );
    ctx.textAlign = "left";
    ctx.fillText("Score: " + score, w/2 - 400, h -350, 150);
    ctx.fillText("Goal: " + goal, w/2 - 400, h - 300, 150);
    ctx.fillText("remaining: " + remainingChars, w/2 -400, h -250, 150);
}

function handleGame(){
    remainingChars = 20 - histStack.length;
    if (remainingChars === 0 || calcStack[calcStack.length - 1] === goal) {
        (remainingChars !== 0) ? score++ : score = 0;
        goal = Math.floor(Math.random() * 1000);
        histStack.length = 0;
        calcStack.length = 0;
    }
}

function animate() {
    ctx.clearRect(0,0,c.width, c.height); // alternativ: ctx.drawImage(background, 0, 0);
    handleGame();
    handleText();
    handleInputBar();
    handleStack();
    requestAnimationFrame( animate );
}

animate();

