@extends('layouts.app')

@section('content')
<div class="container">
    <br><br>
    
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">New Quiz</div>

                <div class="card-body">
                    <form method="POST" action="/quiz/{{ $quiz->quiz_id }}">
                        @csrf
                        @method('PUT')

                        <div class="form-group row">
                            <label for="schedule_code" class="col-md-4 col-form-label text-md-right">{{ __('Schedule Code') }}</label>

                            <div class="col-md-6">
                                <input id="schedule_code" type="text" class="form-control @error('schedule_code') is-invalid @enderror" maxlength="10" name="schedule_code" value="{{ $quiz->schedule_code }}" required autocomplete="off" autofocus>

                                @error('schedule_code')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>



                        <div class="form-group row">

                            <label for="courseid" class="col-md-4 col-form-label text-md-right">{{ __('Course') }}</label>
                             <div class="col-md-6">
                                <select name="category_id" class="form-control">

                                    @foreach($categories as $category)
                                        @if($category->category_id == $quiz->category_id)
                                            <option selected="selected" value="{{ $category->category_id }}">{{ $category->category }}</option>
                                        @else
                                            <option value="{{ $category->category_id }}">{{ $category->category }}</option>
                                        @endif
                                      
                                    @endforeach

                                    
                                </select>
                             </div>
                        </div>


                        <div class="form-group row">

                            <label for="quiz_title" class="col-md-4 col-form-label text-md-right">{{ __('Quiz Title') }}</label>

                            <div class="col-md-6">
                                <input id="quiz_title" type="text" class="form-control @error('quiz_title') is-invalid @enderror" name="quiz_title" value="{{ $quiz->quiz_title }}" required autocomplete="off" autofocus>

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
                                <input id="quiz_desc" type="text" class="form-control @error('quiz_desc') is-invalid @enderror" name="quiz_desc" value="{{ $quiz->quiz_desc }}" required autocomplete="off" autofocus>

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
</div>
@endsection
