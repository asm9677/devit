import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: localStorage.getItem('token') ? localStorage.getItem('token') : sessionStorage.getItem('token'),
    email: localStorage.getItem('email') ? localStorage.getItem('email') : sessionStorage.getItem('email'),
    loading: 0,
  },
  mutations: {
    login(state, payload) {
      if(localStorage.getItem('autoLogin') == 'true') {
        localStorage.setItem('token', payload.token)        
        localStorage.setItem('email', payload.email)
      }
      sessionStorage.setItem('token', payload.token)
      sessionStorage.setItem('email', payload.email)
      state.token = payload.token;
      state.email = payload.email;
    },
    logout(state) {
      localStorage.setItem('autoLogin', 'false')
      localStorage.setItem('token', '')        
      localStorage.setItem('email', '')
      sessionStorage.setItem('token', '')
      sessionStorage.setItem('email', '')      
      state.token = '';
      state.emial = '';
    },
    startLoading(state) {
      state.loading += 1;
    },
    endLoading(state) {
      if(state.loading > 0)
        state.loading -= 1;
    }
  },
  actions: {
    
  },
  modules: {
  }
})