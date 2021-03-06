@extends('layouts.app')

@section('content')



<div class="container">
    <h1>Student Quizzes</h1>
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

    {{-- <a href="/quiz/{{ $quizid }}/question/create" class="btn btn-success mb-2">Add Question</a> --}}

        <div class="row">

            <div class="col-md-4">
                <ul class="list-group">
                    <li class="list-group-item" style="background-color: #25963e; font-weight: bold; color: white;">QUIZ DESCRIPTION</li>
                    <li class="list-group-item" style="font-weight: bold;">ACCESS CODE: <span style="font-style: italic;">{{ $quiz->access_code ? $quiz->access_code : '' }}</span></li>
                    <li class="list-group-item" style="font-weight: bold;">TITLE: <span style="font-style: italic;">{{ $quiz->quiz_title ? $quiz->quiz_title: ''  }}</span></li>
                    <li class="list-group-item" style="font-weight: bold;">DATE TAKEN: <span style="font-style: italic;">{{ $quiz->created_at ? $quiz->created_at: ''  }}</span></li>
                    <li class="list-group-item" style="font-weight: bold;">TOTAL POINTS: <span style="font-style: italic;">{{ $countquiz ? $countquiz: 0 }}</span></li>
                </ul>
            </div>


            <div class="col-md-8">
                <table id="questions" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>ID</th>

                        <th>ID No</th>
                        <th>Lastname</th>
                        <th>Firstname</th>
                        <th>Middlename</th>
                        <th>Score</th>
                        <th>Total Quiz Points</th>

                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                    <tr>
                        <th>ID</th>

                        <th>ID No</th>
                        <th>Lastname</th>
                        <th>Firstname</th>
                        <th>Middlename</th>
                        <th>Score</th>
                        <th>Total Quiz Points</th>
                    </tr>
                    </tfoot>
                </table>
            </div><!--close col-md-8-->
        </div><!--close div table-->

    <hr>


</div> <!-- close container div-->

@endsection

@section('extrascript')

<script src="{{ asset('/js/dataTables.buttons.min.js') }}"></script>
<script src="{{ asset('/js/buttons.flash.min.js') }}"></script>

<script src="{{ asset('/js/jszip.min.js') }}"></script>
<script src="{{ asset('/js/pdfmake.min.js') }}"></script>
<script src="{{ asset('/js/vfs_fonts.min.js') }}"></script>
<script src="{{ asset('/js/buttons.html5.min.js') }}"></script>
<script src="{{ asset('/js/buttons.print.min.js') }}"></script>

  <script type="text/javascript">

		$(document).ready(function(){

            var table = $('#questions').DataTable({
                processing: true,
                dom: 'Bfrtip',
                buttons: [
                  'copy', 'csv', 'excel', 'pdf', 'print'
                ],
                ajax: {
                    url: '/quiz/student-quizzes/ajax/studentquiz/{{ $quizid }}',
                    dataSrc: ''
                },
                columns: [
                    { data: 'quiz_id', visible:false },

                    { data: 'idno' },
                    { data: 'lname' },
                    { data: 'fname' },
                    { data: 'mname' },
                    { data: 'total_score' },
                    { data: 'total_points', visible:false },

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
