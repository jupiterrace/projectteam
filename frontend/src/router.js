
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReviewManager from "./components/ReviewManager"
import GameManager from "./components/GameManager"


import View from "./components/View"
import PurchaseManager from "./components/PurchaseManager"

import PaymentManager from "./components/PaymentManager"

import MessageManager from "./components/MessageManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/reviews',
                name: 'ReviewManager',
                component: ReviewManager
            },
            {
                path: '/games',
                name: 'GameManager',
                component: GameManager
            },


            {
                path: '/views',
                name: 'View',
                component: View
            },
            {
                path: '/purchases',
                name: 'PurchaseManager',
                component: PurchaseManager
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },

            {
                path: '/messages',
                name: 'MessageManager',
                component: MessageManager
            },



    ]
})
