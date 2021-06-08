(ns cc-web-app.home
		(:require
			[c3kit.apron.corec :as ccc]
			[c3kit.wire.js :as wjs]
			[c3kit.wire.websocket :as ws]
			[cc-web-app.layoutc :as layoutc]
			[cc-web-app.page :as page]
			[reagent.core :as reagent]
			))

(defmethod page/render :home [_]
		[:main#-home
			[:section.home
						[:div.container.button-group.text-align-center
							[:a.button.light {:href "https://cleancoders.com/blog/2021-02-16-clean-coders-planning-poker"
																									:target "_blank"} [:span.fas.fa-book] "Article"]]
				[:div.container.small-width
					[:div.endorsement
						[:div.row.mini-margin-bottom
							[:div.column
								[:div.card
									[:div.container
										[:p "Clean Coders Poker adds a whole new dimension to Planning Poker, solving the problem that estimates look like promises."]]]]]
						[:small "James Grenning, creator of Planning Poker"]]]]])

