package model.search;

import model.Member;

public class SearchGreaterThanAgeStrategy implements ISearchStrategy
{
		private int age;
		
		public SearchGreaterThanAgeStrategy(int age)
		{
			this.age = age;
		}

		@Override
		public boolean memberFound(Member a_member) {
			return a_member.getMembersAge() > age;
		}
}
