<template>
  <v-btn icon @click="toggleTheme">
    <v-icon>{{
      isDark ? "mdi-white-balance-sunny" : "mdi-weather-night"
    }}</v-icon>
  </v-btn>
</template>

<script lang="ts" setup>
import { computed } from "vue";
import { useTheme } from "vuetify";

const tema = useTheme();
const isDark = computed(() => tema.global.current.value.dark);

function toggleTheme() {
  const newTheme = isDark.value ? "light" : "dark";
  tema.global.name.value = isDark.value ? "light" : "dark";
  localStorage.setItem("tema", newTheme);
}
</script>
