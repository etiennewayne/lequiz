<?php

namespace App\Http\Middleware;

use Illuminate\Foundation\Http\Middleware\VerifyCsrfToken as Middleware;

class VerifyCsrfToken extends Middleware
{
    /**
     * Indicates whether the XSRF-TOKEN cookie should be set on the response.
     *
     * @var bool
     */
    protected $addHttpCookie = true;

    /**
     * The URIs that should be excluded from CSRF verification.
     *
     * @var array
     */
    protected $except = [
        //
        //'/android/auth',
        '/android/login',
        '/android/quizgame/store',
        '/android/validate/code',

        '/android/category/store',
        '/android/category/update',
        '/android/category/delete',

        '/android/quiz/store',
        '/android/quiz/update',
        '/android/quiz/delete',

        '/android/question/store',
        '/android/question/update',
        '/android/question/delete',

    ];
}
