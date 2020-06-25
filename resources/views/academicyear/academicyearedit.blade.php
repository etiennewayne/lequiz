@extends('layouts.app')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Academic Year</div>

                <div class="card-body">
                    <form method="POST" action="/academicyear/{{ $academicyear->academic_year_id }}">
                        @csrf
                        @method('PUT')
                        <div class="form-group row">

                            <label for="ayCode" class="col-md-4 col-form-label text-md-right">{{ __('Academic Year Code') }}</label>

                            <div class="col-md-6">
                                <input id="ay_code" type="text" class="form-control @error('ay_code') is-invalid @enderror" name="ay_code" value="{{ $academicyear->ay_code }}" required autocomplete="off" autofocus>

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
                                <input id="ay" type="text" class="form-control @error('ay') is-invalid @enderror" name="ay" value="{{ $academicyear->ay }}" required autocomplete="off" autofocus>

                                @error('ay')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="semester" class="col-md-4 col-form-label text-md-right">{{ __('Semester') }}</label>

                            <div class="col-md-6">
                                {{-- <input id="semester" type="text" class="form-control @error('semester') is-invalid @enderror" name="semester" value="{{ old('semester') }}" autocomplete="off" autofocus>
                                 --}}
                                 <select class="form-control" name="semester_id" id="semester_id">

                                    @foreach($semesters as $sem)
                                        @if($sem->semester_id == $academicyear->semester_id)
                                            <option selected="selected" value="{{ $sem->semester_id }}">{{ $sem->semester }}</option>
                                        @else
                                            <option value="{{ $sem->semester_id }}">{{ $sem->semester }}</option>
                                        @endif
                                        
                                    @endforeach
                                 </select>
                                @error('mname')
                                    <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>



                        <div class="form-group row mb-0">
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-success btn-block">
                                    {{ __('UPDATE') }}
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
