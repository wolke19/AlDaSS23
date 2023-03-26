const canvas = document.getElementById("canvas1");
const ctx = canvas.getContext("2d");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

const junctionArray = [];

const middle = {
    x: canvas.width / 2,
    y: canvas.height / 2,
}
const mouse = {
    x: null,
    y: null,
}


// EVENTS_______________________________________________________________________________________________________________
window.addEventListener("resize", function () {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    middle.x = canvas.width/2;
    middle.y = canvas.height/2;
});
addEventListener("click", function (event){
})
canvas.addEventListener("mousemove", function (event){
    mouse.x = event.x;
    mouse.y = event.y;
})

// CLASSES______________________________________________________________________________________________________________
class Junction {
    constructor(number) {
        this.number = number;
        this.group = number;
        this.size = 1;

        this.x = Math.random() * canvas.width;
        this.y = Math.random() * canvas.height;
        this.radius = 40;
        this.speedX = 0;
        this.speedY = 0;
    }

    update() {
        for (let i = 0; i < junctionArray.length; i++) {
            for (let j = 0; j < junctionArray.length; j++) {

            }
        }
    }

    draw(){
        ctx.fillStyle = "hsl(" + (this.group * 36 ) + ", 100%, 50%)";
        ctx.beginPath();
        ctx.arc(this.x,this.y,this.radius,0,Math.PI*2);
        ctx.fill();
        ctx.strokeStyle = "white";
        ctx.font = "15px Arial";
        ctx.strokeText("Number: " + this.number + " | Group: " + this.group + " | Size: " + this.size,
            this.x - this.radius, this.y - this.radius - 5, this.radius * 4);



    }


}

class Edge {
    constructor(junctionA, junctionB) {
        this.JuncA = junctionA;
        this.JuncB = junctionB;

    }

}


// STANDALONE FUNCTIONS_________________________________________________________________________________________________________________
function find(p){
    while (p.group !== p.number) p = p.number;
    return p;
}
function connected(p, q){
    return (find(p) === find(q));
}
function union(p,q){
    let pRoot = find(p);
    let qRoot = find(q);

    if (pRoot === qRoot) return;
    else if (pRoot.size < qRoot.size){
        pRoot.group = qRoot.group;
        qRoot.size += pRoot.size;
    }
    else{
        qRoot.group = pRoot.group;
        pRoot.size = qRoot.size;
    }
}

function init(){
    for (let i = 0; i < 10; i++) {
        junctionArray.push(new Junction(i))
    }
    // union(junctionArray[1], junctionArray[2]);
    // union(junctionArray[2], junctionArray[3]);
    union(junctionArray[3], junctionArray[4]);
    union(junctionArray[4], junctionArray[9]);
}

function prepareNextAnimationFrame(){
    for (let i = 0; i < junctionArray.length; i++) {
        junctionArray[i].update();
        junctionArray[i].draw();
        for (let j = 0; j < junctionArray.length; j++) {

        }
    }
}


// ANIMATION____________________________________________________________________________________________________________
function animate(){
    ctx.clearRect(0,0,canvas.width, canvas.height);
    prepareNextAnimationFrame();
    requestAnimationFrame(animate);
}

// RUN__________________________________________________________________________________________________________________
init();
animate();