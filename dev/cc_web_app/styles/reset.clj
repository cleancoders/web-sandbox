(ns cc-web-app.styles.reset
  (:refer-clojure :exclude [rem])
  (:require
    [cc-web-app.styles.core :refer :all]
    [garden.stylesheet :refer [at-media]]
))

(def screen
(list

[:html {
  :font-family "sans-serif"
  :-ms-text-size-adjust "100%"
  :-webkit-text-size-adjust "100%"
}]

[:body {
  :margin 0
}]

[:article :aside :details :figcaption :figure :footer :header
 :hgroup :main :menu :nav :section :summary {
  :display "block"
}]

[:audio :canvas :progress :video {
  :display "inline-block"
  :vertical-align "baseline"
}]

["audio:not([controls])" {
  :display "none"
  :height 0
}]

["[hidden]" :template {
  :display "none"
}]

[:a {
  :background-color "transparent"
}]

[:a:active :a:hover {
  :outline 0
}]

["abbr[title]" {
  :border-bottom [[(px 1) "dotted"]]
}]

[:dfn {
  :font-style "italic"
}]

[:mark {
  :background "#ff0"
  :color "#000"
}]

[:sub :sup {
  :font-size "75%"
  :line-height 0
  :position "relative"
  :vertical-align "baseline"
}]

[:sup {
  :top (em -0.5)
}]

[:sub {
  :bottom (em -0.25)
}]

[:img {
  :border 0
}]

["svg:not(:root)" {
  :overflow "hidden"
}]

[:figure {
  :margin [[(em 1) (px 40)]]
}]

[:hr {
  :-moz-box-sizing "content-box"
  :box-sizing "content-box"
  :height 0
}]

[:pre {
  :overflow "auto"
}]

[:code :kbd :pre :samp {
  :font-family "monospace, monospace"
  :font-size (em 1)
}]

[:button :input :optgroup :select :textarea {
  :color "inherit"
  :font "inherit"
  :margin 0
}]

[:button {
  :overflow "visible"
}]

[:button :select {
  :text-transform "none"
}]

[:button "input[type='button']" "input[type='reset']" "input[type='submit']" {
  :-webkit-appearance "button"
  :cursor "pointer"
}]

["button[disabled]" "input[disabled]" {
  :cursor "default"
}]

["button::-moz-focus-inner" "input::-moz-focus-inner" {
  :border 0
  :padding 0
}]

[:input {
  :line-height "normal"
}]

["input[type='checkbox']" "input[type='radio']" {
  :box-sizing "border-box"
  :padding 0
}]

["input[type='number']::-webkit-inner-spin-button"
 "input[type='number']::-webkit-outer-spin-button" {
  :height "auto"
}]

["input[type='search']" {
  :-webkit-appearance "textfield"
  :-moz-box-sizing "content-box"
  :-webkit-box-sizing "content-box"
  :box-sizing "content-box"
}]

["input[type='search']::-webkit-search-cancel-button"
 "input[type='search']::-webkit-search-decoration" {
  :-webkit-appearance "none"
}]

[:legend {
  :border 0
  :padding 0
}]

[:textarea {
  :overflow "auto"
}]

[:optgroup {
  :font-weight "bold"
}]

[:h1 :h2 :h3 :h4 :h5 :h6 :p :blockquote :pre
 :a :abbr :acronym :address :big :cite :code
 :del :dfn :em :img :ins :kbd :q :s :samp
 :small :strike :strong :sub :sup :tt :var
 :b :u :i :center :dl :dt :dd :ol :ul :li
 :fieldset :form :label :legend :table
 :caption :tbody :tfoot :thead :tr :th :td
 :article :aside :canvas :details :embed
 :figure :figcaption :footer :header :hgroup
 :menu :nav :output :ruby :section :summary
 :time :mark :audio :video {
  :margin 0
  :padding 0
  :border 0
  :font-size "100%"
  :font "inherit"
  :vertical-align "baseline"
  :-webkit-margin-before 0
  :-webkit-margin-after 0
  :-webkit-padding-start 0
}]

[:body {
  :line-height (rem 1)
}]

[:ol :ul {
  :list-style "none"
}]

[:blockquote :q {
  :quotes "none"
}]

[:blockquote:before :blockquote:after
 :q:before :q:after {
  :content "''"
}]

[:table {
  :border-collapse "collapse"
  :border-spacing 0
}]

))
