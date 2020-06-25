@extends('layouts.app')

@section('content')



<div class="container">
    <h1>Questions</h1>
    <hr>

    @if(session('success'))
        <div class="alert alert-success alert-dismissible fade show" role="alert">
          <strong>SAVED!</strong> {{session('success')}}
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
    @endif

    @if(session('deleted'))
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>DELETED!</strong> {{session('deleted')}}
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
    @endif

    @if(session('updated'))
        <div class="alert alert-success alert-dismissible fade show" role="alert">
          <strong>UPDATED!</strong> {{session('updated')}}
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
    @endif

    @if(session('active'))
        <div class="alert alert-success alert-dismissible fade show" role="alert">
          <strong>ACTIVE!</strong> {{session('updated')}}
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
    @endif


    <a href="/quiz/{{ $quizid }}/question/create" class="btn btn-success mb-2">Add Question</a>

        <div class="row justify-content-center">
            <table id="questions" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Question</th>
                        <th>Option A</th>
                        <th>Option B</th>
                        <th>Option C</th>
                        <th>Option D</th>
                        <th>Answer</th>
                        <th>Time</th>
                        <th>Score</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
                  <tfoot>
                      <tr>
                        <th>ID</th>
                          <th>Question</th>
                          <th>Option A</th>
                          <th>Option B</th>
                          <th>Option C</th>
                          <th>Option D</th>
                          <th>Answer</th>
                          <th>Time</th>
                          <th>Score</th>
                          <th width="140px">Action</th>
                      </tr>
                  </tfoot>
              </table>

        </div><!--close div table-->

    <hr>



</div> <!-- close container div-->

@endsection

@section('extrascript')
	<script type="text/javascript">

		$(document).ready(function(){

            var table = $('#questions').DataTable({
                processing: true,
                ajax: {
                    url: '/quiz/question/ajax/{{ $quizid }}',
                    dataSrc: ''
                },
                columns: [
                    { data: 'question_id', 'visible' : false },
                    { data: 'question' },
                    { data: 'opt_a' },
                    { data: 'opt_b' },
                    { data: 'opt_c' },
                    { data: 'opt_d' },
                    { data: 'ans' },
                    { data: 'set_time' },
                    { data: 'equiv_score' },
                    {
                        defaultContent: '<button class="btn btn-warning btn-sm" id="edit">Edit</button><button class="btn btn-danger btn-sm" id="delete">Delete</button>'
                    }
                ]
            });


            $('#questions tbody').on( 'click', '#edit', function () {
                var data = table.row( $(this).parents('tr') ).data();
                var id = data['question_id'];
                window.location = '/quiz/{{$quizid}}/question/'+id+'/edit' ;

            });//criteria click edit


            $('#questions tbody').on( 'click', '#delete', function () {
                var data = table.row( $(this).parents('tr') ).data();

                var id = data['question_id'];
                var quiz_title = data['question'];

                var token = $("meta[name=csrf-token]").attr('content');
                var method = $("input[name=_method]").val();

                $.confirm({
                    title: 'Are you sure you want to delete '+ quiz_title +'?',
                    theme: 'material',
                    type : 'red',
                    draggable: true,
                    animationBounce: 1.5, // default is 1.2 whereas 1 is no bounce.
                    buttons: {
                        confirm: function () {

                            $.post('/quiz/{{ $quizid}}/question/'+ id,
                              {
                                _token : token,
                                _method : 'DELETE'
                              },

                                function(data, status){
                                    if(status=="success"){
                                        $('#questions').DataTable().ajax.reload();
                                        $.alert('Deleted successfully');
                                    }else{
                                        $.alert('An error occured. ERROR : ' +status);
                                    }

                                });
                        },
                        cancel: function () {

                        },

                    }
                });//confirm box
            });//criteria click delete


		});//document ready


	</script>
@endsection
