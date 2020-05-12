"use strict";


(function () {

    var mainHeader = document.getElementById("main-header");
    var listener = function (event) {
        mainHeader.innerText = listOfRollsFromDieFunc();
    };
    document.getElementById('button').addEventListener('click', listener, false);


    //this will eventually do something with the grid, hopefully


    // dice shaker dice shaker audio

    var box = document.getElementById("boox");
    var grid = function (event) {
        box.id = "boox" + mainHeader.innerText;
        $('audio#dice')[0].play();
        // console.log(box.id);
    };
    document.getElementById('button').addEventListener('click', grid, false);

// changes the main background of main-header1 when dice are rolled


    $('#button').click(function () {
        var boxDiv = 'box' + mainHeader.innerText;
        var color = $(boxDiv).css("background-color");
        $('#main-header1').css('background', color);
        console.log(color);
    });


// gets the individual divs to send their colors to the header


    $(".container1").click(function () {
        var color = $(this).css("background-color");
        $("#main-header1").css('background', color);
        console.log(color);
    });


    //changes font size of the font to be more visible after cli

    $("#button").click(function () {
        $("#main-header").css({"font-size": "40px", 'margin': "8px"});
    });


    //the dice roll function in a nutshell

    var numberOfRolls = document.getElementById('mySelect2');
    // console.log(numberOfRolls);
    var diceFunction = document.getElementById('mySelect');

    // console.log(diceFunction);
    function listOfRollsFromDieFunc() {
        var dieChosen = diceFunction.value;
        // console.log(dieChosen);
        var rollAmount = numberOfRolls.value;
        // console.log(rollAmount);
        var diceArray = [];
        for (var i = 0; i < rollAmount; i++) {
            diceArray.push(Math.floor(Math.random() * dieChosen) + 1);
            console.log(diceArray)
        }
        // console.log(diceArray);
        return diceArray
    }
})();


//some jQuery styling to use or not to use

$('.border').css({
    'border': '1px solid #F00',
    'font-family': 'helvetica',
    'border-radius': '4px',
    'padding': '6px',
    'box-shadow': '0px 0px 5px goldenrod'
});
$('.underestimate').css({'color': 'white', 'background-color': 'grey'});
$('.important').css({'background-color': 'darkgrey', 'font-size': '25px', 'font-family': 'century'});
$('.codeup').css('border', '4px solid red');
$('.inferno').css({'background': 'red', 'color': 'white', 'width': '100%', 'box-shadow': '-2px -2px 5px grey'});
$('.competent').css({'width': '150%', 'background': 'goldenrod', 'padding': '4px'});


// this is the main-header1 roll over dice and the color swap


$('#main-header1').hover(
    function () {
        $('audio#dice')[0].play();

        $(this).css({'color': 'goldenrod'});
        // $(this).css()
    },
    function () {
        $(this).css('color', 'white');
    }
);
$('#main-header').hover(
    function () {
        $(this).css('color', 'goldenrod');
        $(this).fadeIn(200);

        // $(this).css()
    },
    function () {
        $(this).css('color', 'white');
    }
);

// container rollover animation

$(".container1").hover(function () {
    $(this).fadeOut(100);
    $(this).fadeIn(500);
});