@extends('layouts.app')

@section('content')

<div class="container">

   <div class="row justify-content-center mt-5">
       <div class="col-md-6">
            <div class="card">
                <cdiv class="card-header">
                    <h3>Category Information</h3>
                </cdiv>
                <form action="/room/{{ $room->room_id }}" method="post">
                    @csrf
                    @method('PUT')

                    <div class="card-body">

                        <div class="form-group">
                            <label for="room">Room</label>
                            <input id="room" class="form-control @error('room') is-invalid @enderror" type="text" name="room" value="{{ $room->room }}" placeholder="Room" autocomplete="off">
                            @error('room')
                            @php
                                $message = 'The room '. old('category') .' has already been taken.';
                            @endphp
                                <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                </span>
                            @enderror
                        </div>

                        <div class="form-group">
                            <label for="room_desc">Room Description</label>
                            <textarea class="form-control" id="room_desc" rows="3" name="room_desc" placeholder="Description">{{ $room->room_desc }}</textarea>
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

