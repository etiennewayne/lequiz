<!doctype html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CSRF Token -->
    <meta name="csrf-token" content="{{ csrf_token() }}">

    <title>{{ config('app.name', 'Laravel') }}</title>

    <!-- Scripts -->
   {{--   --}}

      {{-- <script src="{{ asset('js/app.js') }}" defer></script> --}}

    <!-- Fonts -->
    {{-- <link rel="dns-prefetch" href="//fonts.gstatic.com"> --}}
    {{-- <link href="https://fonts.googleapis.com/css?family=Nunito" rel="stylesheet"> --}}

    <!-- Styles -->

    {{-- <link href="{{ asset('css/app.css') }}" rel="stylesheet"> --}}

    <script type="text/javascript" src="{{asset('/js/jquery-3.5.1.js')}}"></script>

    <link rel="stylesheet" type="text/css" href="{{ asset("/css/bootstrap.min.css") }}">
    <link rel="stylesheet" type="text/css" href="{{ asset("/css/dataTables.bootstrap4.min.css") }}">
    <link rel="stylesheet" type="text/css" href="{{ asset('css/jquery-confirm.css') }}">
    <link rel="stylesheet" type="text/css" href="{{ asset('css/style.css') }}">
    <style>

        .btn-link {
          background: none!important;
          border: none;
          padding: 0!important;
          /*optional*/
          font-family: arial, sans-serif;
          /*input has OS specific font-family*/
          color: #069;
          text-decoration: underline;
          cursor: pointer;
          text-decoration: none;
        }

    body{
        font-family: "Helvetica Neue", sans-serif;
    }

        .action-btn{
           width: 100px;
        }

        #edit{
            margin: 2px;

        }
        #delete{
            margin: 2px;

        }

        th {
            background-color: #168511;
            color: white;

        }

    </style>




</head>
<body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="">
                    {{ config('app.name', 'Laravel') }}
                </a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="{{ __('Toggle navigation') }}">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- Left Side Of Navbar
                    <ul class="navbar-nav mr-auto">

                    </ul>-->

                    <!-- Right Side Of Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Authentication Links -->
                        @guest
                            <li class="nav-item">
                                <a class="nav-link" href="{{ route('login') }}">{{ __('Login') }}</a>
                            </li>
                           {{--  @if (Route::has('register'))
                                <li class="nav-item">
                                    <a class="nav-link" href="{{ route('register') }}">{{ __('Register') }}</a>
                                </li>
                            @endif --}}
                        @else

                            @if(Auth::user()->classification == 'FACULTY')
                                <li class="nav-item">
                                    <a class="nav-link" href="{{ url('/home') }}">Home</a>
                                </li>

                                <li>
                                    <a class="nav-link" href="{{ url('/category') }}">Category</a>
                                </li>

                                <li>
                                    <a class="nav-link" href="{{ url('/quiz') }}">Quiz</a>
                                </li>

                                {{-- <li>
                                    <a class="nav-link" href="{{ url('/room') }}">Room</a>
                                </li> --}}
                            @endif

                            @if(Auth::user()->classification == 'ADMINISTRATOR')

                                    <li class="nav-item">
                                        <a class="nav-link" href="{{ url('/home') }}">Home</a>
                                    </li>

                                    <li>
                                        <a class="nav-link" href="{{ url('/academicyear') }}">Academic Year</a>
                                    </li>

                                    <li>
                                        <a class="nav-link" href="{{ url('/account') }}">Account</a>
                                    </li>


                                @endif



                                <a class="nav-link" href="{{ route('logout') }}"
                                   onclick="event.preventDefault();
                                                     document.getElementById('logout-form').submit();">
                                    {{ __('Logout') }}
                                </a>
                                <form id="logout-form" action="{{ route('logout') }}" method="POST" style="display: none;">
                                    @csrf
                                </form>
                        @endguest
                    </ul>
                </div>
            </div>
        </nav>



            @yield('content')






    <script type="text/javascript" src="{{asset('/js/bootstrap.min.js')}}"></script>

    <script type="text/javascript" src="{{asset('/js/jquery.dataTables.min.js')}}"></script>
    <script type="text/javascript" src="{{asset('/js/dataTables.bootstrap4.min.js')}}"></script>
    <script type="text/javascript" src="{{asset('/js/jquery-confirm.js')}}"></script>

    {{-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script> --}}



    @yield('extrascript')

</body>
</html>
