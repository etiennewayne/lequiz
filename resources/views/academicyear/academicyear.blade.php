@extends('layouts.app')

@section('content')


    <div class="container">

        <div class="row justify-content-center">

            <div class="col-md-10">

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

                <h3>Academic Year</h3>
                <hr>
                <a href="/academicyear/create" class="btn btn-success mb-2">Add Academic Year</a>
                <table id="academicyears" class="table table-striped table-bordered" style="width:100%">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>A.Y. Code</th>
                        <th>Academic Year</th>
                        <th>Semester</th>
                        <th>Is Active</th>
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>A.Y. Code</th>
                        <th>Academic Year</th>
                        <th>Semester</th>
                        <th>Is Active</th>
                        <th>Action</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div> <!-- end row -->

    </div><!--end container-->



@endsection

@section('extrascript')
    <script type="text/javascript">
        $(document).ready(function() {
            var table = $('#academicyears').DataTable({
                processing:true,
                ajax: {
                    url: '/academicyear/ajax/academicyears',
                    dataSrc: '',
                },
                columns:[
                    { data : 'academic_year_id' },
                    { data : 'ay_code' },
                    { data : 'ay' },
                    { data : 'semester' },
                    { data : 'active' },
                    {
                        defaultContent: '<button class="btn btn-warning btn-sm" id="edit">Edit</button><button class="btn btn-danger btn-sm" id="delete">Delete</button>'
                    },
                ],
            });




            $('#academicyears tbody').on( 'click', '#edit', function () {
                let data = table.row( $(this).parents('tr') ).data();
                let id = data['academic_year_id'];
                window.location = '/academicyear/'+id+'/edit' ;

            });//criteria click edit

            $('#academicyears tbody').on( 'click', '#delete', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['academic_year_id'];
                let contentText = data['ay'];

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

                            $.post('/academicyear/'+ id,
                                {
                                    _token : token,
                                    _method : 'DELETE'
                                },

                                function(data, status){
                                    if(status=="success"){
                                        $('#academicyears').DataTable().ajax.reload();
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

