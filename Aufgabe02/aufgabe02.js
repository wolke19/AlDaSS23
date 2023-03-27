const canvas = document.getElementById("canvas1");
const ctx = canvas.getContext("2d");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

const junctionArray = [];
let animationState = 0;

// CANVAS SETTINGS + LAYOUT CONSTANTS__________________________________________
ctx.textAlign = "center";

const textfieldW = 200;
const textfieldH = 100;
const textfieldX = canvas.width / 2 - textfieldW / 2;
const textfieldY = canvas.height - textfieldH * 1.5;

const mouse = {
    x: null,
    y: null,
}


// EVENTS_______________________________________________________________________________________________________________
window.addEventListener("resize", function () {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
});
canvas.addEventListener("click", function (event){
    if (event.x > textfieldX &&
        event.x < textfieldX + textfieldW / 2 &&
        event.y > textfieldY &&
        event.y < textfieldY + textfieldH / 2){
        animationState = 1;
    }
    else animationState = 0;
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

        this.x = (canvas.width / 2 - 50) + Math.random() * 100;
        this.y = (canvas.height / 2 -50) + Math.random() * 100;
        this.radius = 40;
        this.speedX = .1;
        this.speedY = .1;
    }
    // find(){
    //     let pTemp = this.number;
    //     while (pTemp != this.group){
    //         pTemp = junctionArray[pTemp].group;
    //     }
    //     return junctionArray[pTemp].number;
    // }
    update() {
        // let maxSize = Math.max(...junctionArray.map(o => o.size));
        // if (this.size === maxSize){
        //     this.x = canvas.width/2;
        //     this.y = canvas.height/2;
        //     return;
        // }

        let closePushParameter = 10;
        let pullEdgesParameter = 100;
        let pushAllParameter = 600;

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
        if (this.y < 100 || this.y > canvas.height - (canvas.height - textfieldY + 100)) this.speedY *= -1;

        this.x += this.speedX;
        this.y += this.speedY;
    }
    drawEdges(){
        ctx.moveTo(this.x, this.y);
        ctx.lineTo(junctionArray[this.group].x, junctionArray[this.group].y);
        ctx.stroke();
    //     Wieso teilweise sichtobar??? TODO:fix
    }
    draw(){
        ctx.fillStyle = "hsl(" + 150 + (find(this.number) * 36 ) + ", 100%, 50%)";
        ctx.beginPath();
        ctx.arc(this.x,this.y,this.radius,0,Math.PI*2);
        ctx.fill();
        ctx.strokeStyle = "black";
        ctx.beginPath();
        ctx.arc(this.x,this.y,this.radius,0,Math.PI*2);
        ctx.stroke();

        ctx.fillStyle = "black";
        ctx.font = "16px Arial";
        ctx.fillText("Root: " + this.group + " | Size: " + this.size,
            this.x, this.y - this.radius - 5, this.radius * 2);
        ctx.fillText("group: " + find(this.number), this.x, this.y + 30, this.radius);
        ctx.font = "50px Arial";
        ctx.fillText(this.number, this.x, this.y + 15, this.radius);

    }
}



// STANDALONE FUNCTIONS_________________________________________________________________________________________________________________
function find(p){
    let pTemp = p;
    while (pTemp !== junctionArray[pTemp].group){
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
    }
}

function init(){
    for (let i = 0; i < 10; i++) {
        junctionArray.push(new Junction(i))
    }
    union(1,2);
    union(1,2);

    union(4,6);
    union(0,4);
    console.log()
}

function prepareNextAnimationFrame(){
    for (let i = 0; i < junctionArray.length; i++) {
        junctionArray[i].update();
        junctionArray[i].drawEdges();
        junctionArray[i].draw();
    }
}

function interactivity(){
//     EingabeFeld
    ctx.fillStyle = "white";
    ctx.fillRect(textfieldX, textfieldY + textfieldH / 2, textfieldW, textfieldH/2);
    ctx.fillStyle = "grey"
    ctx.fillRect(textfieldX, textfieldY, textfieldW, textfieldH / 2);

    ctx.strokeStyle = "black";
    ctx.beginPath();
    ctx.moveTo(textfieldX + textfieldW / 2, textfieldY);
    ctx.lineTo(textfieldX + textfieldW / 2, textfieldY + textfieldH / 2);
    ctx.stroke();
    ctx.moveTo(textfieldX, textfieldY + textfieldH/2);
    ctx.lineTo(textfieldX + textfieldW, textfieldY + textfieldH / 2);
    ctx.stroke();
    ctx.strokeRect(textfieldX, textfieldY, textfieldW, textfieldH);
    ctx.fillStyle = "black";
    ctx.font = "12px Arial";
    ctx.fillText("start union input!",textfieldX + 0.25 * textfieldW,textfieldY + 0.25 * textfieldH + 5);
    ctx.fillText("UNION!",textfieldX + 0.75 * textfieldW,textfieldY + 0.25 * textfieldH + 5);
}

// ANIMATION____________________________________________________________________________________________________________
function animate(){
    switch (animationState) {
        case 0: {
            ctx.clearRect(0,0,canvas.width, canvas.height);
            prepareNextAnimationFrame();
            interactivity();
            break;
        }
        case 1:{
            // TODO: insert input1 animation
            break;
        }
        case 2:{
            // TODO: insert input2 animation
            break;
        }

    }
    console.log(animationState);
    requestAnimationFrame(animate);
}

// RUN__________________________________________________________________________________________________________________
init();
animate();
