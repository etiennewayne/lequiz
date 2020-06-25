@extends('layouts.app')

@section('content')

    <div class="container">


        <div class="row">
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

        </div>

        <div class="row justify-content-center">

            <div class="col-md-12">
                <h3>Quizzes</h3>
                <hr>
                <a href="/quiz/create" class="btn btn-success mb-3">Add Quiz</a>
                <table id="quizzes" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Category</th>
                        <th>Quiz Title</th>
                        <th>Quiz Description</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Category</th>
                        <th>Quiz Title</th>
                        <th>Quiz Description</th>
                        <th>Action</th>
                    </tr>
                    </tfoot>
                </table>
            </div>


        </div><!--close div row-->

        <hr>
        <div>

        </div>



    </div> <!-- close container div-->

@endsection

@section('extrascript')

    <script type="text/javascript">
        var btnString = '<button class="btn btn-warning btn-sm" id="edit">Edit</button><button class="btn btn-danger btn-sm" id="delete">Delete</button><button class="btn btn-primary btn-sm" style="margin:2px;" id="btnquestion">Question</button></div>';
        $(document).ready(function() {
            var table = $('#quizzes').DataTable({
                processing:true,
                ajax: {
                    url: '/quiz/ajax/quizzes',
                    dataSrc: '',
                },
                columns:[
                    { data : 'quiz_id' },
                    { data : 'category' },
                    { data : 'quiz_title' },
                    { data : 'quiz_desc' },
                    {
                        defaultContent: btnString
                    },
                ],
            });


            $('#quizzes tbody').on( 'click', '#edit', function () {
                var data = table.row( $(this).parents('tr') ).data();
                var id = data['quiz_id'];
                window.location = '/quiz/'+id+'/edit' ;

            });//criteria click edit

            $('#quizzes tbody').on( 'click', '#btnquestion', function () {
                var data = table.row( $(this).parents('tr') ).data();
                var id = data['quiz_id'];
                window.location = '/quiz/'+id+'/question';
                // console.log('test');
            });

            $('#quizzes tbody').on( 'click', '#delete', function () {
                var data = table.row( $(this).parents('tr') ).data();

                var id = data['quiz_id'];
                var quiz_title = data['quiz_title'];

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

                            $.post('/quiz/'+ id,
                                {
                                    _token : token,
                                    _method : 'DELETE'
                                },

                                function(data, status){
                                    if(status=="success"){
                                        $('#quizzes').DataTable().ajax.reload();
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

        });
    </script>
@endsection
