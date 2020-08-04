<template>
  <v-container fluid>
    <v-layout align-start justify-center>
      <v-spacer></v-spacer>
      <v-flex xs4 class="elevation-1 pa-3 ma-2"><v-list>
        <draggable v-model="myArray" @start="drag=true" @end="drag=false">
            
                <v-list-item v-for="element in myArray" :key="element.id">
                    <v-divider />
                    {{element.name}}
                    <v-divider />
                </v-list-item>
            
            <!-- <div v-for="element in myArray" :key="element.id" style="border:1px solid red">{{element.name}}</div> -->
        </draggable></v-list>
      </v-flex>
      <v-flex xs4 class="grey lighten-2 pa-2">
        {{myArray}}
      </v-flex>
      <v-spacer></v-spacer>
    </v-layout>
  </v-container>
</template>

<script>
import draggable from "vuedraggable";
export default {
  name: 'v-tabs',
  components: {
    draggable
  },
  methods: {
  },
  data() {
    return {
      active: 0,
      myArray: [
        {
          id: 1, 
          name: "1st Tab",
          text: "This is a 1st tab"
        },
        {
          id: 2, 
          name: "2nd Tab",
          text: "This is a 2nd tab"
        },
        {
          id: 3, 
          name: "3rd Tab",
          text: "This is a 3rd tab"
        }
      ]
    }
  }
}
</script>