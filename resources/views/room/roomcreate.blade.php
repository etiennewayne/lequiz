@extends('layouts.app')

@section('content')

  <!-- Modal -->
  <div class="modal fade" id="modal-category" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLongTitle">Browse Categories</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <table id="categories" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </tfoot>

            </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          {{-- <button type="button" class="btn btn-primary">Save changes</button> --}}
        </div>
      </div>
    </div>
  </div>


  <!-- Modal -->
  <div class="modal fade" id="modal-quiz" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLongTitle">Browse Quizzes</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <table id="quizzes" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Quiz Title</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>Quiz Title</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </tfoot>

            </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          {{-- <button type="button" class="btn btn-primary">Save changes</button> --}}
        </div>
      </div>
    </div>
  </div>


<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card">
                <div class="card-header">{{ __('Room Information') }}</div>

                <div class="card-body">
                    <form method="POST" action="/room">
                        @csrf

                        <div class="form-group row">
                            <label for="room" class="col-md-4 col-form-label text-md-right">{{ __('Room') }}</label>

                            <div class="col-md-6">
                                <input id="room" type="text" class="form-control @error('room') is-invalid @enderror" name="room" value="{{ old('room') }}" required autocomplete="off" autofocus>

                                @error('room')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="room_desc" class="col-md-4 col-form-label text-md-right">{{ __('Room Description') }}</label>

                            <div class="col-md-6">
                                <input id="room_desc" type="text" class="form-control" name="room_desc" value="{{ old('room_desc') }}" required autocomplete="off" autofocus>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="access_code" class="col-md-4 col-form-label text-md-right">{{ __('Access Code') }}</label>

                            <div class="col-md-6">
                                <input id="access_code" type="text" class="form-control @error('access_code') is-invalid @enderror" name="access_code" value="{{ old('access_code') }}" autocomplete="off" readonly autofocus>
                                @error('access_code')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror

                                <button id="btnMD5" class="btn btn-info btn-sm mt-2">
                                    {{ __('Generate Access Code') }}
                                </button>


                            </div>
                        </div>


                        <div class="form-group row">
                            <label for="category" class="col-md-4 col-form-label text-md-right">{{ __('Category') }}</label>

                            <div class="col-md-6">
                                <input id="category" type="text" class="form-control @error('category') is-invalid @enderror" name="category" value="{{ old('category') }}" required readonly autocomplete="off">
                                <input type="hidden" id="category_id" name="category_id" value="{{ old('category_id') }}">
                                @error('category')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror


                                <button type="button" class="btn btn-info btn-sm mt-2" data-toggle="modal" data-target="#modal-category">
                                    {{ __('Browse Category') }}
                                </button>

                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="quiz_title" class="col-md-4 col-form-label text-md-right">{{ __('Quizzes') }}</label>

                            <div class="col-md-6">
                                <input id="quiz_title" type="text" class="form-control @error('quiz_title') is-invalid @enderror" name="quiz_title" value="{{ old('quiz_title') }}" required readonly autocomplete="quiz_title">
                                <input type="hidden" id="quiz_id" name="quiz_id" value="{{ old('quiz_id') }}">
                                @error('quiz_title')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror


                                <button type="button" id="btnBrowseQuiz" class="btn btn-info btn-sm mt-2">
                                    {{ __('Browse Quiz') }}
                                </button>

                                <span class="my-alert">this is sampole</span>


                            </div>
                        </div>


                        <div class="form-group row mb-0">
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-success btn-block">
                                    {{ __('Save') }}
                                </button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript" src="{{ asset('js/myjs.js') }}"></script>
    <script type="text/javascript" src="{{ asset('js/roomcreate.js') }}"></script>

</div>





@endsection


