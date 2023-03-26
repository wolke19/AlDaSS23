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

        this.x = canvas.width / 4 + Math.random() * 300;
        this.y = canvas.height / 4 + Math.random() * 300;
        this.radius = 40;
        this.speedX = .1;
        this.speedY = .1;
    }
    update() {
        let maxSize = Math.max(...junctionArray.map(o => o.size));
        // if (this.size === maxSize){
        //     this.x = canvas.width/2;
        //     this.y = canvas.height/2;
        //     return;
        // }

        let closePushParameter = 7;
        let pullEdgesParameter = 50;
        let pushAllParameter = 300;

        this.speedX /= 1.1;
        this.speedY /= 1.1;

        for (let i = 0; i < junctionArray.length; i++) {
            if (i === this.number) continue;
            const dx = junctionArray[i].x - this.x;
            const dy = junctionArray[i].y - this.y;
            const distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < this.radius * 3){
                this.speedX -= dx / (closePushParameter * distance);
                this.speedY -= dy / (closePushParameter * distance);
            }
            else if (this.group === junctionArray[i].group && distance >= this.radius * 5) {
                this.speedX += dx / (pullEdgesParameter * distance);
                this.speedY += dy / (pullEdgesParameter * distance);
            }
            else if (distance < this.radius * 8){
                this.speedX -= dx / (pushAllParameter * distance);
                this.speedY -= dy / (pushAllParameter * distance);
            }


        }

        // Rebound from walls
        if (this.x < 100 || this.x > canvas.width - 100) this.speedX *= -1;
        if (this.y < 100 || this.y > canvas.height - 100) this.speedY *= -1;

        this.x += this.speedX;
        this.y += this.speedY;
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

        ctx.moveTo(this.x, this.y);
        ctx.lineTo(junctionArray[this.group].x, junctionArray[this.group].y);
        ctx.stroke();
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
    let pTemp = p;
    while (pTemp != junctionArray[pTemp].group){
        pTemp = junctionArray[pTemp].group;
    }
    return junctionArray[pTemp].number;
}
function connected(p, q){
    return (find(p) === find(q));
}
function union(p,q){
    let pRoot = find(junctionArray[p].number);
    let qRoot = find(junctionArray[q].number);

    if (pRoot === qRoot) return;
    else if (junctionArray[pRoot].size < junctionArray[qRoot].size){
        junctionArray[pRoot].group = qRoot;
        junctionArray[qRoot].size += junctionArray[pRoot].size;
    }
    else{
        junctionArray[qRoot].group = pRoot;
        junctionArray[pRoot].size += junctionArray[qRoot].size;
        console.log("hey")
    }
}

function init(){
    for (let i = 0; i < 10; i++) {
        junctionArray.push(new Junction(i))
    }
    union(1,2);
    union(1,2);
    union(2,3);
    union(4,6);
    union(0,4);
    union(4,3);
    console.log()
}

function prepareNextAnimationFrame(){
    for (let i = 0; i < junctionArray.length; i++) {
        junctionArray[i].update();
        junctionArray[i].draw();
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