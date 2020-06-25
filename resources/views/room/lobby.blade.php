<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

    <title>{{ config('app.name', 'Laravel') }}</title>

<!--<script type="text/javascript" src="{{asset('/js/jquery-3.5.1.js')}}"></script>-->

    <link rel="stylesheet" type="text/css" href="{{ asset("/css/bootstrap.min.css") }}">
<!-- <link rel="stylesheet" type="text/css" href="{{ asset("/css/dataTables.bootstrap4.min.css") }}">
    <link rel="stylesheet" type="text/css" href="{{ asset('css/jquery-confirm.css') }}">-->
    <link rel="stylesheet" type="text/css" href="{{ asset('css/style.css') }}">


</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-md-4">
            <h3 style="padding-top: 20px;">Waiting for players...</h3>

            <ul id="player-list"></ul>
        </div>

        <div class="col-md-8">
            QUESTION HERE...

            <div id="question"></div>
        </div>
    </div>



    <br>
    <button class="btn btn-primary" id="start-quiz">START QUIZ</button>

</div>



<script type="text/javascript" src="/js/myjs.js"></script>
<script type="text/javascript">

    var acode = '{{ $accesscode }}';

    let txtbox = document.getElementById('msg');
    let btnstartquiz = document.getElementById('start-quiz');


    var i = 0; //for looping data

    let Debug = function(msg) {
        console.log(msg);
    }



    var ws = new WebSocket('ws://192.168.254.5:8080');
    ws.onopen = function(e) {
        Debug("Connection established!");
    };

    ws.onmessage = function(e) {
        Debug(e.data);

        let obj = JSON.parse(e.data);
        Debug(obj);

        if(obj.key === 'joining' && obj.accessCode === acode){
            let node = document.createElement("li");
            node.appendChild(document.createTextNode(obj.player));
            document.getElementById("player-list").appendChild(node);
        }
    };

    ws.onclose = function(e){
        Debug('close con' + e.data);
    };


    btnstartquiz.addEventListener('click', function(){
    	i = 0;
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                myLoop(JSON.parse(this.responseText));
            }
        };
        xhttp.open("GET", "/quiz/question/ajax/question-by-access-code/" +acode , true);
        xhttp.send();

    });


    // let x = setInterval(function(){
    //     clearInterval(x);
    //     Debug('test');
    // },1000);

    function startQuestion(data){
       // let obj = JSON.parse(data);
        Debug(data.length);
    }

    var sec = 0;//  set your counter to 1
    function myLoop(data) {         //  create a loop function
        setTimeout(function() {   //  call a 3s setTimeout when the loop is called
            if (i < data.length) {
                document.getElementById("question").innerHTML = data[i].question;   //  your code here
                ws.send(JSON.stringify(data[i])); //send data in websocket
                sec = data[i].set_time;
                i++;        //  if the counter < 10, call the loop function
                myLoop(data);             //  ..  again which will trigger another
            }else{
                Debug('end');
            }                       //  ..  setTimeout()
        }, sec * 1000)

    }



</script>



</body>
</html>
