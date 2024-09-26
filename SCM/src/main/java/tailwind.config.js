module.exports = {
  content: [
    'C:/Study/JAVA/SpringBoot/Project/SCM/src/main/resources/templates/**/*.{html,js}',  // For Thymeleaf templates
    'C:/Study/JAVA/SpringBoot/Project/SCM/src/main/resources/static/js/**/*.js',         // If you have custom JS files
    'C:/Study/JAVA/SpringBoot/Project/SCM/src/main/resources/static/css/**/*.css',       // Tailwind classes in your CSS
	'./node_modules/flowbite/**/*.js'													// Flowbite Import	
  ],
  theme: {
    extend: {},
  },
  plugins: [
          require('flowbite/plugin')
      ],
}
