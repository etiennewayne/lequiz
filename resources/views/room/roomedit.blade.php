@extends('layouts.app')

@section('content')

<div class="container">
    
   <div class="row justify-content-center">
       <div class="col-md-6">
            <div class="card">
                <cdiv class="card-header">
                    <h3>Category Information</h3>
                </cdiv>
                <form action="/category/{{ $category->category_id }}" method="post">
                    @csrf
                    @method('PUT')

                    <div class="card-body">
                        
                        <div class="form-group">
                            <label for="category">Category</label>
                            <input id="category" class="form-control @error('category') is-invalid @enderror" type="text" name="category" value="{{ $category->category }}" placeholder="Category" required autocomplete="off">
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
                            <label for="category_desc">Category Description</label>
                            <textarea class="form-control" id="category_desc" rows="3" name="category_desc" placeholder="Description">{{ $category->category_desc }}</textarea>
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

