@extends('layouts.app')

@section('content')

    <div class="container mt-5">
        <div class="row justify-content-center">
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
        </div>




        <div class="row justify-content-center">

            <div class="col-md-10">
                <h3>Rooms</h3>
                <hr>
                <a href="/room/create" class="btn btn-success" style="margin-bottom: 20px;">New Room</a>

                <table id="rooms" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Room Name</th>
                        <th>Description</th>
                        <th>Access Code</th>
                        <th>Quiz ID</th>
                        <th>Quiz Title</th>
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Room Name</th>
                        <th>Description</th>
                        <th>Access Code</th>
                        <th>Quiz ID</th>
                        <th>Quiz Title</th>
                        <th width="200px">Action</th>
                    </tr>
                    </tfoot>

                </table>

            </div>
        </div> <!--close row -->

    </div><!--container-->

@endsection

@section('extrascript')
    <script type="text/javascript">
        $(document).ready(function() {
            var table = $('#rooms').DataTable({
                processing: true,
                ajax: {
                    url: '/room/ajax/rooms',
                    dataSrc: ''
                },
                columns: [
                    { data: 'room_id' },
                    { data: 'room' },
                    { data: 'room_desc' },
                    { data: 'access_code' },
                    { data: 'quiz_id', visible : false, searchable : false },
                    { data: 'quiz_title' },
                    {
                        defaultContent: '<button class="btn btn-warning btn-sm" id="edit">Edit</button><button class="btn btn-danger btn-sm" id="delete">Delete</button></button><button class="btn btn-primary btn-sm" style="margin:2px;" id="startquiz">Start Quiz</button>'
                    },

                ],
            });



            $('#rooms tbody').on( 'click', '#edit', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['room_id'];
                window.location = '/room/'+id+'/edit' ;

            });//criteria click edit

            $('#rooms tbody').on( 'click', '#startquiz', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['access_code'];
                window.location = '/room/lobby/'+id;

            });//criteria click edit



            $('#rooms tbody').on( 'click', '#delete', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['room_id'];
                let contentText = data['room'];

                let token = $("meta[name=csrf-token]").attr('content');
                let method = $("input[name=_method]").val();


                $.confirm({
                    title: 'Are you sure you want to delete '+ contentText +'?',
                    theme: 'material',
                    type : 'red',
                    draggable: true,
                    animationBounce: 1.5, // default is 1.2 whereas 1 is no bounce.
                    buttons: {
                        confirm: function () {

                            $.post('/room/'+ id,
                                {
                                    _token : token,
                                    _method : 'DELETE'
                                },

                                function(data, status){
                                    if(status=="success"){
                                        $('#rooms').DataTable().ajax.reload();
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

