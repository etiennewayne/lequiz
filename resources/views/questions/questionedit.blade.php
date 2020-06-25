@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Edit Question</div>
                    
                <div class="card-body">
                <form method="POST" action="/quiz/{{ $quizid }}/question/{{ $question->question_id }}">
                        @csrf
                        @method('PUT')

                        <div class="form-group row">

                            <label for="question" class="col-md-4 col-form-label text-md-right">{{ __('Question') }}</label>

                            <div class="col-md-6">
                                <textarea id="question" class="form-control @error('question') is-invalid @enderror" name="question" rows="3" required autocomplete="off" autofocus>{{ $question->question }}</textarea>

                                @error('question')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="opt_a" class="col-md-4 col-form-label text-md-right">{{ __('Option A') }}</label>

                            <div class="col-md-6">
                                <input id="opt_a" type="text" class="form-control @error('opt_a') is-invalid @enderror" name="opt_a" value="{{ $question->opt_a }}" required autocomplete="off" autofocus>

                                @error('opt_a')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="opt_b" class="col-md-4 col-form-label text-md-right">{{ __('Option B') }}</label>

                            <div class="col-md-6">
                                <input id="opt_b" type="text" class="form-control @error('opt_b') is-invalid @enderror" name="opt_b" value="{{ $question->opt_b }}" required autocomplete="off" autofocus>

                                @error('opt_b')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="opt_c" class="col-md-4 col-form-label text-md-right">{{ __('Option C') }}</label>

                            <div class="col-md-6">
                                <input id="opt_c" type="text" class="form-control @error('opt_c') is-invalid @enderror" name="opt_c" value="{{ $question->opt_c }}" required autocomplete="off" autofocus>

                                @error('opt_c')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="opt_d" class="col-md-4 col-form-label text-md-right">{{ __('Option D') }}</label>

                            <div class="col-md-6">
                                <input id="opt_d" type="text" class="form-control @error('opt_d') is-invalid @enderror" name="opt_d" value="{{ $question->opt_d }}" required autocomplete="off" autofocus>

                                @error('opt_d')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="set_time" class="col-md-4 col-form-label text-md-right">{{ __('Set Time') }}</label>

                            <div class="col-md-6">
                                <input id="set_time" type="number" class="form-control @error('set_time') is-invalid @enderror" name="set_time" value="{{ $question->set_time }}" required autocomplete="off" autofocus>

                                @error('set_time')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <!-- Choices of answer -->
                        <div class="form-group row">
                            <label for="ans" class="col-md-4 col-form-label text-md-right">{{ __('Answer') }}</label>

                            <div class="col-md-6">
                                <select class="form-control" name="ans" id="ans" required>
                                    @if(strcasecmp($question->ans, 'a') == 0)
                                        <option selected="selected" value="A">A</option>
                                        <option value="b">B</option>
                                        <option value="c">C</option>
                                        <option value="d">D</option>
                                    @elseif(strcasecmp($question->ans, 'b') == 0)
                                        <option value="a">A</option>
                                        <option selected="selected" value="B">B</option>
                                        <option value="c">C</option>
                                        <option value="d">D</option> 
                                    @elseif(strcasecmp($question->ans, 'c') == 0)
                                        <option value="a">A</option>
                                        <option value="b">B</option>
                                        <option selected="selected" value="c">C</option>
                                        <option value="d">D</option> 
                                    @elseif(strcasecmp($question->ans, 'd') == 0)
                                        <option value="a">A</option>
                                        <option value="b">B</option>
                                        <option value="c">C</option>
                                        <option selected="selected" value="d">D</option>
                                    @endif
                                </select>
                            </div>
                        </div>

                        <div class="form-group row mb-0">
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    {{ __('Save') }}
                                </button>

                                <a class="btn btn-danger" style="color:white;" href="{{ url()->previous() }}">Back</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
