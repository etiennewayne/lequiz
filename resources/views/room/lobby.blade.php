@extends('layouts.app')

@section('content')

<div id="app">

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

</div><!--close id VUE-->









<script type="text/javascript" src="/js/myjs.js"></script>
<!--<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script> -->
<!--
<script>
    var app = new Vue({
      el: '#app',
      
      data: {
        message: 'Hello Vue!'
      },

      mounted(){
        
      },

      methods: {
      
      }
    })



</script>-->


<script type="text/javascript">

 var servername = '<?php echo $_SERVER['SERVER_NAME']; ?>';

//=================set WEBSOCKET ADDRESS==============
    var websocketAddress = 'ws://'+ servername + ':8080';
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
        const li = document.querySelectorAll('ul li');

   
        let obj = JSON.parse(e.data);
        Debug(obj);


        //Debug(obj[0].access_code);
        if(obj[0].key === 'joining' && obj[0].access_code === acode){
            let node = document.createElement("li");

            for (let i = 0; i < li.length; i++) {
                // it is the same as equal to
                if (li[i].textContent !== obj[0].player) {
                    //console.log('I am the <li> you want', li[i]);
                    li[i].parentNode.removeChild(li[i]);
                }

            }


            node.appendChild(document.createTextNode(obj[0].player));
            document.getElementById("player-list").appendChild(node);
        }

        if(obj[0].key === 'exit'){

            Debug('exit' + obj[0].player);

            for (let i = 0; i < li.length; i++) {
                // it is the same as equal to
                console.log(li[i].textContent);
                if (li[i].textContent === obj[0].player) {
                    //console.log('I am the <li> you want', li[i]);
                    //li[i].parentNode.removeChild(li[i]);
                }

            }
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

