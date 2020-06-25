@extends('layouts.app')

@section('content')

    <div class="container">
        <div class="row">
            <div class="col">
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
            </div> <!--row-->

        </div>




        <div class="row justify-content-center">

            <div class="col-md-8">
                <h3>Categories</h3>
                <hr>
                <a href="/category/create" class="btn btn-success" style="margin-bottom: 20px;">New Category</a>

                <table id="categories" class="table table-striped table-bordered" style="width:100%;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Category</th>
                        <th>Category Description</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Category</th>
                        <th>Category Description</th>
                        <th>Action</th>
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
            var table = $('#categories').DataTable({
                processing: true,
                ajax: {
                    url: '/category/ajax/categories',
                    dataSrc: ''
                },
                columns: [
                    { data: 'category_id' },
                    { data: 'category' },
                    { data: 'category_desc' },
                    {
                        defaultContent: '<button class="btn btn-warning btn-sm" id="edit">Edit</button><button class="btn btn-danger btn-sm" id="delete">Delete</button>'
                    },

                ],
            });



            $('#categories tbody').on( 'click', '#edit', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['category_id'];
                window.location = '/category/'+id+'/edit' ;

            });//criteria click edit

            $('#categories tbody').on( 'click', '#delete', function () {
                let data = table.row( $(this).parents('tr') ).data();

                let id = data['category_id'];
                let contentText = data['category'];

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

                            $.post('/category/'+ id,
                                {
                                    _token : token,
                                    _method : 'DELETE'
                                },

                                function(data, status){
                                    if(status=="success"){
                                        $('#categories').DataTable().ajax.reload();
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

