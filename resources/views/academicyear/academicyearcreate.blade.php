@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Academic Year</div>

                <div class="card-body">
                    <form method="POST" action="/academicyear">
                        @csrf

                        <div class="form-group row">

                            <label for="ay_code" class="col-md-4 col-form-label text-md-right">{{ __('Academic Year Code') }}</label>

                            <div class="col-md-6">
                                <input id="ay_code" type="text" class="form-control @error('ay_code') is-invalid @enderror" name="ay_code" value="{{ old('ay_code') }}" required autocomplete="off" autofocus>

                                @error('ay_code')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="ay" class="col-md-4 col-form-label text-md-right">{{ __('Academic Year') }}</label>

                            <div class="col-md-6">
                                <input id="ay" type="text" class="form-control @error('ay') is-invalid @enderror" name="ay" value="{{ old('ay') }}" required autocomplete="off" autofocus>

                                @error('ay')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="semester_id" class="col-md-4 col-form-label text-md-right">{{ __('Semester') }}</label>

                            <div class="col-md-6">
          
                                 <select class="form-control @error('semester_id') is-invalid @enderror" name="semester_id" id="semester_id">
                                    @foreach($semesters as $sem)
                                        <option value="{{ $sem->semester_id }}">{{ $sem->semester }}</option>
                                    @endforeach
                                 </select>

                                @error('semester_id')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>



                        <div class="form-group row mb-0">
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-success btn-block">
                                    {{ __('SAVE') }}
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
