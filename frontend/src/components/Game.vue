<template>

<v-card style="width:300px; margin-left:5%;" outlined>
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img
      style="width:290px; height:150px; border-radius:10px; position:relative; margin-left:5px; top:5px;"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>

    <v-card-title v-if="value._links">
        Game # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
    </v-card-title >
    <v-card-title v-else>
        Game
    </v-card-title >

    <v-card-text style = "margin-left:-15px; margin-top:10px;">

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field type="number" label="GameId" v-model="value.gameId"/>
          </div>
          <div class="grey--text ml-4" v-else>
            GameId :  {{value.gameId }}
          </div>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field label="PurchaseStatus" v-model="value.purchaseStatus"/>
          </div>
          <div class="grey--text ml-4" v-else>
            PurchaseStatus :  {{value.purchaseStatus }}
          </div>


          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field type="number" label="Version" v-model="value.version"/>
          </div>
          <div class="grey--text ml-4" v-else>
            Version :  {{value.version }}
          </div>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field type="number" label="Price" v-model="value.price"/>
          </div>
          <div class="grey--text ml-4" v-else>
            Price :  {{value.price }}
          </div>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field label="DownloadStatus" v-model="value.downloadStatus"/>
          </div>
          <div class="grey--text ml-4" v-else>
            DownloadStatus :  {{value.downloadStatus }}
          </div>


          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-menu
                v-model="menu"
                width="290px"
            >
                <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="value.playtime"
                    label="Playtime"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
                </template>
                <v-date-picker
                v-model="value.playtime"
                :min="new Date().toISOString().substr(0, 10)"
                @input="menu = false"
                ></v-date-picker>
            </v-menu>
          </div>
          <div class="grey--text ml-4" v-else>
            Playtime :  {{value.playtime }}
          </div>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field type="number" label="DownloadId" v-model="value.downloadId"/>
          </div>
          <div class="grey--text ml-4" v-else>
            DownloadId :  {{value.downloadId }}
          </div>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field type="number" label="CustomerId" v-model="value.customerId"/>
          </div>
          <div class="grey--text ml-4" v-else>
            CustomerId :  {{value.customerId }}
          </div>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field type="number" label="ReviewCount" v-model="value.reviewCount"/>
          </div>
          <div class="grey--text ml-4" v-else>
            ReviewCount :  {{value.reviewCount }}
          </div>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-menu
                v-model="menu"
                width="290px"
            >
                <template v-slot:activator="{ on, attrs }">
                <v-text-field
                    v-model="value.releaseDate"
                    label="ReleaseDate"
                    prepend-icon="mdi-calendar"
                    readonly
                    v-bind="attrs"
                    v-on="on"
                ></v-text-field>
                </template>
                <v-date-picker
                v-model="value.releaseDate"
                :min="new Date().toISOString().substr(0, 10)"
                @input="menu = false"
                ></v-date-picker>
            </v-menu>
          </div>
          <div class="grey--text ml-4" v-else>
            ReleaseDate :  {{value.releaseDate }}
          </div>


    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-actions style = "position:absolute; right:0; bottom:0;">
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="edit"
        v-if="!editMode"
      >
        Edit
      </v-btn>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="save"
        v-else
      >
        Save
      </v-btn>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="remove"
        v-if="!editMode"
      >
        Delete
      </v-btn>
      
    </v-card-actions>
  </v-card>


</template>

<script>
  const axios = require('axios').default;
  
  
  
  
  
  
  
  
  
  
  export default {
    name: 'Game',
    components:{
    },
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        date: new Date().toISOString().substr(0, 10),
    }),
    created(){
    },

    methods: {
      edit(){
        this.editMode = true;
      },
      async save(){
        try{
          var temp = null;
          if(this.isNew){
            temp = await axios.post(axios.fixUrl('/games'), this.value)
          }else{
            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
          }

          this.value = temp.data;

          this.editMode = false;
          this.$emit('input', this.value);

          if(this.isNew){
            this.$emit('add', this.value);
          }else{
            this.$emit('edit', this.value);
          }

        }catch(e){
          alert(e.message)
        }
      },
      async remove(){
        try{
          await axios.delete(axios.fixUrl(this.value._links.self.href))
          this.editMode = false;
          this.isDeleted = true;

          this.$emit('input', this.value);
          this.$emit('delete', this.value);

        }catch(e){
          alert(e.message)
        }
      },
    },
  }
</script>

