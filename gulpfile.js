var gulp = require('gulp');
var watch = require('gulp-watch');
var livereload = require('gulp-livereload');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var fs = require('fs');
var del = require('del');
var gutil = require('gulp-util');
var targetLocation = './target/classes/static/js/react';
var REACT_FILES = [ './src/front-end/react/**/*.js'];
var streamify = require('gulp-streamify');
var uglify = require('gulp-uglify');

var pageURL = 'http://localhost:8000';

function Bundle() {

    
    
    var Bundler = browserify({
        entries: 'src/front-end/react/index.js',
        transform: [["babelify", {"presets": ["es2015","react"]}]],
        extensions: ['.js'],
        debug: true,
        cache: {},
        packageCache: {},
        fullPaths: true
    });  
    return Bundler
            .bundle()
            .on('error', console.error);
}

gulp.task('react-build-dev', function () {
    Bundle()
           .pipe(source('bundle.js'))
           .pipe(gulp.dest(targetLocation))
           .on('finish', function ( ) {
                gutil.log("build bundle end");
                 livereload.reload(pageURL);
            });
    ;
});

gulp.task('react-build', function () {


    function prodBundle()
    {
        var prodBundler = browserify({
            entries: 'src/front-end/react/index.js',
            transform: [["babelify", {"presets": ["es2015", "react"]}], 
              ["envify", {NODE_ENV: 'production', 
              'global': true, '_': 'purge', }]],
            extensions: ['.js'],
            debug: true,
            cache: {},
            packageCache: {},
            fullPaths: false
        });
        return prodBundler
                .bundle()
                .on('error', console.error);

    }


    prodBundle()
            .pipe(source('bundle.js'))
            .pipe(streamify(uglify()))
            .pipe(gulp.dest(targetLocation  ))
            .on('finish', function ( ) {
                gutil.log("finished release build");
                 
            });;
});




gulp.task('clean', function (  ) {

    del([targetLocation+'/bundle.js']);

});


gulp.task('frontend-watch', function () {


    watch(REACT_FILES, function (events, done) {

        gulp.start('react-build-dev');
    });

    

 

});

gulp.task('init-dev',['react-build-dev' ]);
gulp.task('dev',['frontend-watch' ]);
gulp.task('release',['react-build']);
gulp.task('default',['react-build']);