@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center mt-2">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">New Question</div>

                <div class="card-body">
                <form method="POST" action="/quiz/{{ $quizid }}/question">
                        @csrf

            
                        <div class="form-group row">

                            <label for="question" class="col-md-4 col-form-label text-md-right">{{ __('Question') }}</label>

                            <div class="col-md-6">
                                <textarea id="question" class="form-control @error('question') is-invalid @enderror" name="question" rows="3" value="{{ old('question') }}" required autocomplete="off" autofocus></textarea>

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
                                <input id="opt_a" type="text" class="form-control @error('opt_a') is-invalid @enderror" name="opt_a" value="{{ old('opt_a') }}" required autocomplete="off" autofocus>

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
                                <input id="opt_b" type="text" class="form-control @error('opt_b') is-invalid @enderror" name="opt_b" value="{{ old('opt_b') }}" required autocomplete="off" autofocus>

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
                                <input id="opt_c" type="text" class="form-control @error('opt_c') is-invalid @enderror" name="opt_c" value="{{ old('opt_c') }}" required autocomplete="off" autofocus>

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
                                <input id="opt_d" type="text" class="form-control @error('opt_d') is-invalid @enderror" name="opt_d" value="{{ old('opt_d') }}" required autocomplete="off" autofocus>

                                @error('opt_d')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>


                        <div class="form-group row">
                            <label for="time_limit" class="col-md-4 col-form-label text-md-right">{{ __('Time Limit') }}</label>

                            <div class="col-md-6">
                                <input id="time_limit" type="number" class="form-control @error('time_limit') is-invalid @enderror" name="time_limit" value="{{ old('time_limit') }}" required autocomplete="off" autofocus>

                                @error('time_limit')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>



                    <div class="form-group row">
                        <label for="points" class="col-md-4 col-form-label text-md-right">{{ __('Point(s)') }}</label>

                        <div class="col-md-6">
                            <input id="points" type="number" class="form-control @error('points') is-invalid @enderror" name="points" value="{{ old('points') }}" required autocomplete="off" autofocus>

                            @error('points')
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
                                    <option value="A">A</option>
                                    <option value="B">B</option>
                                    <option value="C">C</option>
                                    <option value="D">D</option>
                                </select>
                            </div>
                        </div>



                        <input type="hidden" name="quiz_id" value="{{ $quizid }}"/>


                        <div class="form-group row mb-0">
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary" style="width:100%;">
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
