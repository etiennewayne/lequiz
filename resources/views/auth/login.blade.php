<!doctype html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

    <title>{{ config('app.name', 'Laravel') }}</title>


    <script type="text/javascript" src="{{asset('/js/jquery-3.5.1.js')}}"></script>

    <link rel="stylesheet" type="text/css" href="{{ asset("/css/bootstrap.min.css") }}">
    <link rel="stylesheet" type="text/css" href="{{ asset("/css/dataTables.bootstrap4.min.css") }}">
    <link rel="stylesheet" type="text/css" href="{{ asset('css/jquery-confirm.css') }}">
    <link rel="stylesheet" type="text/css" href="{{ asset('css/style.css') }}">


</head>



<body>



<div class="container mt-5">

    <div class="row align-items-center">
        <div class="col-6 mx-auto">

            <div class="card">

                <div class="card-header" style="background-color: #2b2828; color: white;">
                    <h3>
                        Security Check
                    </h3>
                </div>

                <div class="card-body">
                    <form method="POST" action="{{ route('login') }}">
                        @csrf

                        <div class="form-group row">
                            <label for="username" class="col-md-4 col-form-label text-md-right">{{ __('Username') }}</label>

                            <div class="col-md-6">
                                <input id="username" type="text" class="form-control @error('username') is-invalid @enderror" name="username" value="{{ old('username') }}" required autocomplete="off" autofocus>

                                @error('username')
                                <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="password" class="col-md-4 col-form-label text-md-right">{{ __('Password') }}</label>

                            <div class="col-md-6">
                                <input id="password" type="password" class="form-control @error('password') is-invalid @enderror" name="password" required autocomplete="current-password">

                                @error('password')
                                <span class="invalid-feedback" role="alert">
                                        <strong>{{ $message }}</strong>
                                    </span>
                                @enderror
                            </div>
                        </div>



                        <div class="form-group row mb-0">
                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary btn-block">
                                    {{ __('Login') }}
                                </button>

                            </div>
                        </div>

                    </form>

                </div>
            </div>
            <!--end card-->
        </div>


    </div>
</div>

</body>

</html>
