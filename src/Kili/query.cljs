(ns Kili.query
  (:require
   [clojure.core.async :as a
    :refer [chan put! take! close! offer! to-chan! timeout
            sliding-buffer dropping-buffer
            go >! <! alt! alts! do-alts
            mult tap untap pub sub unsub mix unmix admix
            pipe pipeline pipeline-async]]
   [clojure.string]
   [cljs.core.async.impl.protocols :refer [closed?]]
   [cljs.core.async.interop :refer-macros [<p!]]
   [goog.string.format]
   [goog.string :refer [format]]
   [goog.object]
   [cljs.reader :refer [read-string]]

   [datahike.api :as Arthur-Dent.api]

   [Kili.seed :refer [root op]]))


(defmulti q :q)

(defmethod q :all-attributes
  [{:keys [conn]
    :as opts}]
  (Arthur-Dent.api/q '[:find [?ident ...]
                        :where [_ :db/ident ?ident]]
                      @conn))