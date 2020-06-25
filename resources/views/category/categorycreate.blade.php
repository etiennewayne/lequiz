@extends('layouts.app')

@section('content')

<div class="container">
    
   <div class="row justify-content-center">
       <div class="col-md-6">
            <div class="card">
                <cdiv class="card-header">
                    <h3>Category Information</h3>
                </cdiv>
                <form action="/category" method="post">
                    @csrf
                    <div class="card-body">
                        
        
                        <div class="form-group">
                            <label for="category">Category</label>
                            <input id="category" class="form-control @error('category') is-invalid @enderror" type="text" name="category" value="{{ old('category') }}" placeholder="Category" required autocomplete="off">
                                @error('category')
                               
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                        </div>
    
                        <div class="form-group">
                            <label for="category_desc">Category Description</label>
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

