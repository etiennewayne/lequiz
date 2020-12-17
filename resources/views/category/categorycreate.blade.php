@extends('layouts.app')

@section('content')

<div class="container mt-5">
    
   <div class="row justify-content-center">
       <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3>Course Information</h3>
                </div>
                <form action="/category" method="post">
                    @csrf


                    <div class="card-body">
                        
                        <div class="form-group">
                            <label for="ay">Academic Year</label>
                            <select class="form-control" name="ay" id="ay">
                                @foreach($academicyear as $ay)
                                     <option value="{{ $ay->academic_year_id}}">{{ $ay->ay_code }}</option>
                                @endforeach
                            </select>
                        </div>
        
                        <div class="form-group">
                            <label for="category">Course</label>
                            <input id="category" class="form-control @error('category') is-invalid @enderror" type="text" name="category" value="{{ old('category') }}" placeholder="Course" required autocomplete="off">
                                @error('category')
                               
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                        </div>
    
                        <div class="form-group">
                            <label for="category_desc">Course Description</label>
                            <textarea class="form-control" id="category_desc" rows="3" name="category_desc" placeholder="Description"></textarea>
                        </div>
                        <button class="btn btn-success btn-block">SAVE</button>
                    </div>

                   

                </form>
                
            </div>
       </div><!--col md-6-->
     
    </div>


</div><!--container-->

@endsection

@section('extrascript')
	<script type="text/javascript">
		$(document).ready(function() {
		    
		});
	</script>
@endsection

