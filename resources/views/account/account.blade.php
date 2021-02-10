@extends('layouts.app')

@section('content')

    <div class="container">

        <h3>Accounts</h3>
        <hr>
        <div class="row mt-2">
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

        <div class="row">
            <div class="col-md-12">
                <a href="/account/create" class="btn btn-success mb-3">Add Account</a>
                <table id="accounts" class="table table-striped table-bordered" style="width:100%;">
                    <thead>
                    <tr>
                        <th>User ID</th>
                        <th>ID No</th>
                        <th>Username</th>
                        <th>Lastname</th>
                        <th>Firstname</th>
                        <th>Middlename</th>
                        <th>Email</th>
                        <th>Classification</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                    <tfoot>
                    <tr>
                        <th>User ID</th>
                        <th>ID No</th>
                        <th>Username</th>
                        <th>Lastname</th>
                        <th>Firstname</th>
                        <th>Middlename</th>
                        <th>Email</th>
                        <th>Classification</th>
                        <th>Action</th>
                    </tr>
                    </tfoot>
                </table>

            </div>

        </div><!--close div row-->
    </div>



@endsection

@section('extrascript')
    <script type="text/javascript">

        $(document).ready(function() {

            var token = $("meta[name=csrf-token]").attr('content');

            var table = $('#accounts').DataTable({
                processing: true,
                ajax: {
                    url: '/account/ajax/users',
                    dataSrc: ''
                },
                columns: [
                    { data: 'user_id', visible : false },
                    { data: 'idno' },
                    { data: 'username' },
                    { data: 'lname' },
                    { data: 'fname' },
                    { data: 'mname' },
                    { data: 'email' },
                    { data: 'classification' },
                    {
                        defaultContent: '<div style="display:block;"><button class="btn btn-warning btn-sm" id="edit">Edit</button><button class="btn btn-danger btn-sm" id="delete">Delete</button><button class="btn btn-success btn-sm" id="rpwd">Reset Password</button></div>'
                    },

                ],
            });



            $('#accounts tbody').on( 'click', '#edit', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['user_id'];
                window.location = '/account/'+id+'/edit' ;

            });//criteria click edit

            $('#accounts tbody').on( 'click', '#delete', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['user_id'];
                let contentText = data['username'];


                let method = $("input[name=_method]").val();


                $.confirm({
                    title: 'Are you sure you want to delete '+ contentText +'?',
                    theme: 'material',
                    type : 'red',
                    draggable: true,
                    animationBounce: 1.5, // default is 1.2 whereas 1 is no bounce.
                    buttons: {
                        confirm: function () {

                            $.post('/account/'+ id,
                                {
                                    _token : token,
                                    _method : 'DELETE'
                                },

                                function(data, status){
                                    if(status=="success"){
                                        $('#accounts').DataTable().ajax.reload();
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



            $('#accounts tbody').on( 'click', '#rpwd', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['user_id'];


                $.post('/account/reset-password/' +id,
                    {
                        _token : token,
                    },

                    function(data, status){
                        if(data[0].status=="success"){
                            $.alert('Your new password is : ' +data[0].newpwd);
                        }else{
                            $.alert('An error occured. ERROR : ' +status);
                        }

                    });


            });//reset password click edit




        });
    </script>
@endsection

