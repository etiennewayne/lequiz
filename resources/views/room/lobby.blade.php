@extends('layouts.app')

@section('content')

<div class="container mt-5">

    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-header">
                  Player List(s)
                </div>
                <div class="card-body">
                    <ul id="player-list"></ul>
                </div>
              </div>
        </div>

        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    QUESTION HERE...
                </div>
                <div class="card-body">
                    <div style="display: none;" id="key"></div>
                    <div id="question"></div>
                </div>
            </div>
        </div>
    </div>

    <button class="btn btn-primary mt-2" id="start-quiz">START QUIZ</button>

</div>






<script type="text/javascript" src="/js/myjs.js"></script>
<script type="text/javascript">

//=================set WEBSOCKET ADDRESS==============
    var websocketAddress = 'ws://192.168.88.242:8080';
//====================================================
//====================================================
//=
//

    var acode = '{{ $accesscode }}';

    let txtbox = document.getElementById('msg');
    let btnstartquiz = document.getElementById('start-quiz');


    var i = 0; //for looping data

    let Debug = function(msg) {
        console.log(msg);
    }



    var ws = new WebSocket(websocketAddress);
    ws.onopen = function(e) {
        Debug("Connection established!");
    };

    ws.onmessage = function(e) {
        //Debug(e.data);

        let obj = JSON.parse(e.data);
        Debug(obj);
        Debug(obj[0].access_code);
        if(obj[0].key === 'joining' && obj[0].access_code === acode){
            let node = document.createElement("li");
            node.appendChild(document.createTextNode(obj[0].player));
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
                //let jsonData = JSON.parse(this.responseText);
                let jsonData = JSON.parse(this.responseText);
                myLoop(JSON.parse(this.responseText));
               jsonData.push("");
               Debug(jsonData);
            }
        };
        xhttp.open("GET", "/quiz/question/ajax/question-by-access-code/" +acode , true);
        xhttp.send();

    });


    function startQuestion(data){
       // let obj = JSON.parse(data);
        Debug(data.length);
    }

    var sec = 0;//  set your counter to 1
    function myLoop(data) {
              //  create a loop function

        setTimeout(function() {   //  call a 0s setTimeout when the loop is called
            if (i < data.length) {

                if(i === data.length-1){
                    //data[i].push("key");
                }

                document.getElementById("key").innerHTML = data.length;
                document.getElementById("question").innerHTML = (i+1) + '. ' + data[i].question;   //  your code here
                ws.send(JSON.stringify(data[i])); //send data in websocket
                Debug(data[i]);
                sec = data[i].set_time;
                i++;
                //  if the counter < 10, call the loop function
                myLoop(data);             //  ..  again which will trigger another
            }else{
                sec=0;
                Debug(JSON.stringify('end'));
                ws.send(JSON.stringify({status:"end"}));

            }                       //  ..  setTimeout()
        }, sec * 1000)

    }



</script>

@endsection

