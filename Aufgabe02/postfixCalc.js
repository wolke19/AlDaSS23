let c = document.getElementById("c"),
    w = c.width = window.innerWidth,
    h = c.height = window.innerHeight,
    ctx = c.getContext( '2d' ),

    // background = new Image(w, h),

    opts = {
        color: "white",
    },


    tick = 0,
    stack = [],
    checkInput = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/"];

ctx.fillStyle = "black";
// background.src = "Aufgabe02/andrew-c-31-00000.jpg";




// EVENTS   --------------------------------------------------------------------------------------------------
addEventListener("keypress", function (event){
    if (checkInput.includes(event.key)){
        stack.push(event.key);
    }

})


// FUNCTIONS --------------------------------------------------------------------------------------------------
function handleInputBar(){
    ctx.fillStyle = "white";
    ctx.fillRect(300, h - 200, w - 600, 100);
    ctx.fillStyle = "black";
    ctx.font = "30px Arial";
    ctx.textAlign = "center";
    ctx.fillText(stack.join("  "), w/2, h - 135, w - 600);
}


function animate() {
    ctx.clearRect(0,0,c.width, c.height); // ctx.drawImage(background, 0, 0, );
    tick++;
    ctx.fillStyle = "grey";
    ctx.textAlign = "center";
    ctx.font = "30px Command";
    ctx.fillText("Aufgabe 2 - Postfix Calculator", w/2, 100, w);

    handleInputBar();







    requestAnimationFrame( animate );
}


animate();

// ...
