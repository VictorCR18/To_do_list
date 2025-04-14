<template>
  <v-app>
    <v-main>
      <div class="position-fixed top-0 right-0 ma-4">
        <BotaoTema />
      </div>
      <router-view />
    </v-main>
  </v-app>
</template>

<script lang="ts" setup>
import BotaoTema from "./components/BotaoTema.vue";

//
</script>
