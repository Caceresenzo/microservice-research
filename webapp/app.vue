<template>
  <v-app>
  <v-app-bar>

    <v-btn variant="text" @click="toggleTheme">
      <v-icon>{{ `mdi-${themeIcon}` }}</v-icon>
    </v-btn>

  </v-app-bar>
    <v-main>
      <v-row>
        <v-col>
          <v-card>
            <v-card-title>Hello</v-card-title>
          </v-card>
        </v-col>
      </v-row>
    </v-main>
  </v-app>
</template>
<script setup lang="ts">
import { useTheme } from 'vuetify';

const theme = useTheme();

const emit = defineEmits<{ (e: 'theme-changed', value: string): void }>();

const themeIcon = computed(() =>
  theme.current.value.dark ? 'cart' : 'cart',
);

function toggleTheme() {
  theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark'
  // emit('theme-changed', theme.current.value.dark ? 'light' : 'dark');
}

const x = ref(1);
</script>