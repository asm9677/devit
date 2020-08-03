import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: sessionStorage.getItem('token'),
    email: sessionStorage.getItem('email')
  },
  mutations: {
    login(state, payload) {
      sessionStorage.setItem('token', payload.token)
      sessionStorage.setItem('email', payload.email)
      state.token = payload.token;
      state.email = payload.email;
    },
    logout(state) {
      sessionStorage.setItem('token', '')
      sessionStorage.setItem('email', '')
      state.token = '';
      state.emial = '';
    }
  },
  actions: {
    
  },
  modules: {
  }
})