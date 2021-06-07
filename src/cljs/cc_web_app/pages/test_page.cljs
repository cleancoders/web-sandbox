(ns cc-web-app.pages.test-page
		(:require [cc-web-app.page :as page]))

(defmethod page/render :pages/test-page [_]
		[:main {:id "-home"}
			[:section {:class "home"}
				[:div {:class "container small-width huge-padding-top"}
					[:div {:class "card margin-bottom"}
						[:div {:class "container text-align-center"}
							[:img {:src "/images/logos/studio-color-transparent.png" :class "margin-bottom"}]
							[:h2 {:class "large-margin-bottom"} "Planning Poker"]]
						[:div {:class "container"}
							[:hr {:class "margin-bottom"}]
							[:div {:class "hgroup medium-margin-bottom"}
								[:h2 "Rooms"
									[:span {:class "note far fa-question-circle tooltip-anchor"}]]
								[:button {:id "-create-room-button" :class "primary"} "Create"]]
							[:ul {:id "-interactive" :class "interactive small-margin-bottom"}
								[:li {:id "-room-R8NLT"}
									[:span {:class "name"} "R8NLT - "]
									[:a {:href "/room/R8NLT"}
										[:button {:class "primary"} "Join"]]]
								[:li {:id "-room-MES3R"}
									[:span {:class "name"} "MES3R - "]
									[:a {:href "/room/MES3R"}
										[:button {:class "primary"} "Join"]]]]
							]]]]]
		)

