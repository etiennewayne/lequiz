@extends('layouts.app')

@section('content')

<div class="container">

  <h1>Courses</h1>
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


      <div class="row justify-content-center">
          	<table id="course" class="table table-striped table-bordered" style="width:100%">
                  <thead>
                      <tr>
                          <th>ID</th>
                          <th>Course Name</th>
                          <th>Action</th>
                      </tr>
                  </thead>
                  <tbody>
                  	@foreach($courses as $course)
                  		<tr>
                  		    <td>{{ $course->courseID }}</td>
                  		    <td>{{ $course->courseName }}</td>
                  		    <

                           <td>
                              <a href="/course/{{ $course->courseID }}/edit">Edit</a> |
                                <form style="display: inline;" action="course/{{ $course->courseID }}" method="post">
                                    @csrf
                                    @method("DELETE")
                                    <input type="submit" class="btn-link" name="submit" value="Delete">

                                </form> 
                                    
                           </td>
                  		</tr>
                  	@endforeach
                  </tbody>
                  <tfoot>
                      <tr>
                         <th>ID</th>
                          <th>Course Name</th>
                          <th>Action</th>
                    
                      </tr>
                  </tfoot>
              </table>  
         
      </div><!--close div table-->

    <hr>
        <div>
            <a href="/course/create" class="btn btn-primary">Add Course</a>  
        </div>



</div> <!-- close container div-->

@endsection

@section('extrascript')
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#course').DataTable();
		});
	</script>
@endsection