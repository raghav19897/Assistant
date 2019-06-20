var b;
var c;
var d;
var e;

var desc = 'Welcome to your homepage assistant.<br>Find answers to almost all your questions.<br>Trying to find a movie or a show?<br>Just ask and save time searching on different sites, Ill search for you and take you there.<br>Want to buy a new product? Try asking me, compare directly on Flipkart & Amazon.<br>Take notes and save reminders.<br>All of this in one place';
var descArray = desc.split('');

var count = 0;
var letterCount=0;
var lineCount=0;

function type() {
    if(letterCount<descArray.length){
        if(descArray[letterCount]!=='<'){
            document.getElementById('description').innerHTML += descArray[letterCount];
            letterCount++;
        }
        else {
            document.getElementById('description').innerHTML += '<br>';
            letterCount=letterCount+4;
            lineCount++;
        }
    }
}


function collapseReminders() {
    var rotate = -45;
    setInterval(function () {if(rotate<=0){document.getElementById('add_reminder').style.transform = 'rotate('+rotate+'deg)';} rotate=rotate+1},10);
    clearInterval(b);
    clearInterval(c);
    clearInterval(d);
    clearInterval(e);
    document.getElementById('reminder_box').style.height = '0';
    document.getElementById('reminder_box').style.visibility = 'hidden';
    document.getElementById('date_pick').style.height = '0';
    document.getElementById('date_pick').style.visibility = 'hidden';
    document.getElementById('time_set').style.height = '0';
    document.getElementById('time_set').style.visibility = 'hidden';
    document.getElementById('reminder_but').style.height = '0';
    document.getElementById('reminder_but').style.visibility = 'hidden';
}

var innerHeight = window.innerHeight;
var height=0;
var heightP=0;

function expandReminders(){
    var rotate = 0;
    setInterval(function () {if(rotate>=-45){document.getElementById('add_reminder').style.transform = 'rotate('+rotate+'deg)';} rotate=rotate-1},10);
    b = setInterval(function () {
        expand(4,'reminder_box')
    },1);
    c = setInterval(function () {
        expand(4,'date_pick')
    },1);

    d = setInterval(function () {
        expand(4,'time_set')
    },1);

    e = setInterval(function () {
        expand(3,'reminder_but')
    },1);
}

function showHideReminders() {
    if (count%2===0){
        heightP=0;
        height=0;
        expandReminders();
    }
    else {
        collapseReminders();
    }
    count++;
}

function expand(max,id) {
    document.getElementById(id).style.visibility = 'visible';
    if(heightP<=max){
        height = height + 0.1;
        heightP = (height / innerHeight) * 100;
        document.getElementById(id).style.height = heightP + '%';
    }
}

var oldX;
var oldY;
var newX;
var newY=0;
function setCanvas(){
    console.log((20/window.innerHeight)*100);
    console.log((document.getElementById('notes_save_but').offsetWidth/window.innerWidth)*100);
    document.getElementById('myCanvas').width = innerWidth;
    document.getElementById('myCanvas').height = innerHeight;
}

function forLine(event) {
    oldX = event.clientX;
    oldY = event.clientY;
}

var count=0;

function setCircles(event) {
    var canvas = document.getElementById("myCanvas");
    var ctx = canvas.getContext("2d");
    var ctx1 = canvas.getContext("2d");
    ctx.beginPath();
    newX = event.clientX;
    newY = event.clientY;
    ctx.arc(newX, newY, 1, 0, 2 * Math.PI);
    ctx.strokeStyle = 'white';
    ctx.stroke();
    if (count > 0) {
        ctx1.moveTo(oldX, oldY);
        ctx1.lineTo(newX, newY);
        ctx1.lineWidth = 0.5;
        ctx1.stroke();
    }
    forLine(event);
    count++;
}


