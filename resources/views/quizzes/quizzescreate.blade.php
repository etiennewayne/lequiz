@extends('layouts.app')

@section('content')
<br><br>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">New Quiz</div>

                <div class="card-body">
                    <form method="POST" action="/quiz">
                        @csrf


                        <div class="form-group row">

                            <label for="courseid" class="col-md-4 col-form-label text-md-right">{{ __('Category') }}</label>
                             <div class="col-md-6">
                                <select name="category_id" class="form-control">

                                    @foreach($categories as $category)
                                      <option value="{{ $category->category_id }}">{{ $category->category }}</option>
                                    @endforeach

                                    
                                </select>
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

                            <label for="quiz_title" class="col-md-4 col-form-label text-md-right">{{ __('Quiz Title') }}</label>

                            <div class="col-md-6">
                                <input id="quiz_title" type="text" class="form-control @error('quiz_title') is-invalid @enderror" name="quiz_title" value="{{ old('quiz_title') }}" required autocomplete="off" autofocus>

                                @error('quiz_title')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="quiz_desc" class="col-md-4 col-form-label text-md-right">{{ __('Quiz Description') }}</label>

                            <div class="col-md-6">
                                <input id="quiz_desc" type="text" class="form-control @error('quiz_desc') is-invalid @enderror" name="quiz_desc" value="{{ old('quiz_desc') }}" required autocomplete="off" autofocus>

                                @error('quiz_desc')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
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
