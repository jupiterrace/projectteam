<template>

<v-data-table
    :headers="headers"
    :items="view"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'View',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "viewId", value: "viewId" },
            { text: "gameId", value: "gameId" },
            { text: "reviewCount", value: "reviewCount" },
            { text: "purchaseStatus", value: "purchaseStatus" },
            { text: "downloadStatus", value: "downloadStatus" },
            { text: "customerId", value: "customerId" },
            { text: "version", value: "version" },
        ],
        view : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/views')

      this.view = temp.data._embedded.views;

    },
    methods: {
    }
  }
</script>

