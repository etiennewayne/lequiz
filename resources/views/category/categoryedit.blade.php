@extends('layouts.app')

@section('content')

<div class="container">

   <div class="row justify-content-center mt-5">
       <div class="col-md-6">
            <div class="card">
                <cdiv class="card-header">
                    <h3>Course Information</h3>
                </cdiv>
                <form action="/category/{{ $category->category_id }}" method="post">
                    @csrf
                    @method('PUT')

                    <div class="card-body">



                        <div class="form-group">
                            <label for="category">Course Code</label>
                            <input id="category" class="form-control @error('category') is-invalid @enderror" type="text" name="category" value="{{ $category->category }}" placeholder="Course" required autocomplete="off">
                            @error('category')
                            @php
                                $message = 'The category '. old('category') .' has already been taken.';
                            @endphp
                                <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                </span>
                            @enderror
                        </div>

                        <div class="form-group">
                            <label for="category_desc">Course Title</label>
                            <textarea class="form-control" id="category_desc" rows="3" name="category_desc" placeholder="Description">{{ $category->category_desc }}</textarea>
                        </div>


                        <div class="form-group">
                            <label for="unit">Unit</label>
                            <input id="unit" class="form-control @error('unit') is-invalid @enderror" type="number" name="unit" value="{{ $category->unit }}" placeholder="Unit" required autocomplete="off">
                            @error('unit')
                            <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                            @enderror
                        </div>


                        <button class="btn btn-success btn-block">UPDATE</button>
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

