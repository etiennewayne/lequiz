
var quiz_url = '/room/create/ajax/quizzes/cid/0';
var table_quizzes;
var access_code = document.getElementById("access_code");

var aBrowseQuiz  = document.getElementById('alert-browsequiz');


$(document).ready(function() {

    category();
    quiz();

    document.getElementById('btnMD5').addEventListener("click", function(e){
        e.preventDefault();
        var currentDate = new Date();
        var md5string = MD5(currentDate.getTime().toString());
        access_code.value = md5string.substring(0,6);
    });


    document.getElementById('btnBrowseQuiz').addEventListener("click", function(e){
        e.preventDefault();

        let category_id = document.getElementById("category_id").value;

        if(category_id.length > 0){

            table_quizzes.ajax.url(quiz_url).load();
            $('#modal-quiz').modal('show');
        }

    });


});


function category(){
    var table = $('#categories').DataTable({
        processing: true,
        ajax: {
            url: '/room/create/ajax/categories',
            dataSrc: ''
        },
        columns: [
            { data: 'category_id' },
            { data: 'category' },
            { data: 'category_desc' },
            {
                defaultContent: '<button class="btn btn-outline-success" id="edit">SELECT</button>'
            },

        ],
        // buttons: [
        //     {
        //         text: 'RELOAD',
        //         action: function(){
        //             alert('test');
        //         }
        //     }
        // ],
    });


    $('#categories tbody').on( 'click', '#edit', function () {
        var data = table.row( $(this).parents('tr') ).data();
        document.getElementById('category').value = data['category'];
        var category_id = data['category_id'];
        document.getElementById('category_id').value = data['category_id'];
        quiz_url = '/room/create/ajax/quizzes/cid/' + category_id;

        $('#modal-category').modal('hide')

    });
}



function quiz(){


    table_quizzes = $('#quizzes').DataTable({
        processing: true,
        ajax: {
            url: quiz_url,
            dataSrc: ''
        },
        columns: [
            { data: 'quiz_id' },
            { data: 'quiz_title' },
            { data: 'quiz_desc' },
            {
                defaultContent: '<button class="btn btn-outline-success" id="edit">SELECT</button>'
            },

        ],
    });

    //$('#modal-quiz').modal('show');


    $('#quizzes tbody').on( 'click', '#edit', function () {
        var data = table_quizzes.row( $(this).parents('tr') ).data();
        document.getElementById('quiz_title').value = data['quiz_title'];
        document.getElementById('quiz_id').value = data['quiz_id'];
        $('#modal-quiz').modal('hide')
    });
}
