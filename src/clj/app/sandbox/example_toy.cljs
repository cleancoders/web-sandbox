(ns app.sandbox.example-toy
		(:require [app.page :as page]))

(defmethod page/render :sandbox/example-toy [_]
		[:main {:id "-home"}
			[:section {:class "home"}
				[:div {:class "container small-width huge-padding-top"}
					[:div {:class "card margin-bottom"}
						[:div {:class "container text-align-center"}
							[:img {:src "/images/logos/studio-color-transparent.png" :class "margin-bottom"}]
							[:h2 {:class "large-margin-bottom"} "A Web-App Template"]]
						[:div {:class "container"}
							[:hr {:class "margin-bottom"}]
							[:div {:class "hgroup medium-margin-bottom"}
								[:h2 "Example Sandbox Toys"]]
								[:ul {:id "-interactive" :class "interactive small-margin-bottom"}
									[:li {:id "pail"}
										[:span {:class "name"} "pail"]]
									[:li {:id "shovel"}
										[:span {:class "name"} "shovel"]]
									[:li {:id "castle-mold"}
										[:span {:class "name"} "castle mold"]]
									[:li {:id "toy-bot"}
										[:span {:class "name"} "toy boat"]]
									[:li {:id "monster trucks"}
										[:span {:class "name"} "monster trucks"]]]
							]]]]]
		)


