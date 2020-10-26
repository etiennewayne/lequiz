<?php

namespace App\Http\Middleware;

use Closure;
use Auth;


class FacultyMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {

        if(strtolower(Auth::user()->classification) == 'faculty'){
            return $next($request);
        }
        abort(403);
    }
}
